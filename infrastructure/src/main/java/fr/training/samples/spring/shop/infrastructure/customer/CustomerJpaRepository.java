package fr.training.samples.spring.shop.infrastructure.customer;

import fr.training.samples.spring.shop.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<Customer,String> {
    Customer findByName(String name);


}
