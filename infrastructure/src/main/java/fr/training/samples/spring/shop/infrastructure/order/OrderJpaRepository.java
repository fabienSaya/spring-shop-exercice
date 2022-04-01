package fr.training.samples.spring.shop.infrastructure.order;

import fr.training.samples.spring.shop.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<Order,String> {
    List<Order> findByCustomerId(String customerId);

}
