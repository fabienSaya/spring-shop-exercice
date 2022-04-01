package fr.training.samples.spring.shop.application.customer;

import fr.training.samples.spring.shop.domain.common.exception.AlreadyExistingException;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public Customer create(Customer customer) {
        Customer customerWithSameName= customerRepository.findByCustomerName(customer.getName());
        if (customerWithSameName!=null)
            throw new AlreadyExistingException("A customer with this name already exist");

        customerRepository.save(customer);
        return customer;

    }

    @Transactional(readOnly = true)
    @Override
    public Customer findOne(String customerId) {
        return customerRepository.findById(customerId);
    }
}
