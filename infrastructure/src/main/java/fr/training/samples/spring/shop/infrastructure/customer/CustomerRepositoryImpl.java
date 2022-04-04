package fr.training.samples.spring.shop.infrastructure.customer;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Customer findById(String id) {
        /*Optional<Customer> customerOptional= customerJpaRepository.findById(id);
        if (customerOptional.isPresent())
            return customerOptional.get();
        else
            return null;*/
        return customerJpaRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void save(Customer customer) {
        customerJpaRepository.save(customer);
    }

    @Override
    public Customer findByCustomerName(String name) {
        return customerJpaRepository.findByName(name);
    }

    @Override
    public List<Customer> findAll() {
        return customerJpaRepository.findAll();
    }
}
