package fr.training.samples.spring.shop.infrastructure.order;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private OrderJpaRepository orderJpaRepository;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order findById(String orderId) {
        return orderJpaRepository.findById(orderId).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void save(Order order) {
        orderJpaRepository.save(order);
    }

    @Override
    public List<Order> findAllByCustomreId(String customerId) {
        return orderJpaRepository.findByCustomerId(customerId);
    }
}
