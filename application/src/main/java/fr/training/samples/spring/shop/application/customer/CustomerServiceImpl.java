package fr.training.samples.spring.shop.application.customer;

import fr.training.samples.spring.shop.domain.common.exception.AlreadyExistingException;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.customer.RoleTypeEnum;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public Customer create(Customer customer) {
        Customer customerWithSameName = customerRepository.findByCustomerName(customer.getName());
        if (customerWithSameName != null)
            throw new AlreadyExistingException("A customer with this name already exist");

        //on encode le password
        customer.updatePassword(passwordEncoder.encode(customer.getPassword()));

        //on fait une copie du customer en encodant le password
        /*Customer customer1 = Customer.builder()
                .id(customer.getId())
                .name(customer.getName())
                .password(passwordEncoder.encode(customer.getPassword()))
                .addRole(RoleTypeEnum.ROLE_USER).build();*/

        // Encode given password
        //customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        // New customer has user role by default
        //customer.addRole(RoleTypeEnum.ROLE_USER);

        customerRepository.save(customer);
        return customer;

    }

    @Transactional(readOnly = true)
    @Override
    public Customer findOne(String customerId) {
        return customerRepository.findById(customerId);
    }

    @Secured("ROLE_USER")
    @Transactional
    @Override
    public void update(Customer customer) {
        // Encode given password
        //customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        Customer customer1 = Customer.builder()
                .name(customer.getName())
                .password(passwordEncoder.encode(customer.getPassword()))
                .addRole(RoleTypeEnum.ROLE_USER).build();

        customerRepository.save(customer1);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
