package fr.training.samples.spring.shop.application.customer;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.item.Item;

import java.util.List;

public interface CustomerService {
    /**
     * Create a new customer
     * @param customer the Customer to create
     * @return the created Customer
     */
    Customer create(Customer customer);

    /**
     * Retrieve a customer according to the given identifier.
     * @param customerId the customer identifier
     * @return the retrieved Customer
     */
    Customer findOne(String customerId);

    void update(Customer customer);

    List<Customer> getAllCustomers();
}
