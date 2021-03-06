package fr.training.samples.spring.shop.domain.customer;

import java.util.List;

public interface CustomerRepository {

	Customer findById(String id);

	void save(Customer customer);

	Customer findByCustomerName(final String name);

	List<Customer> findAll();

}
