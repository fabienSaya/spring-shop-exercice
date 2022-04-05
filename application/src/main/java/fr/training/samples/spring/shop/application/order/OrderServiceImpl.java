package fr.training.samples.spring.shop.application.order;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderItem;
import fr.training.samples.spring.shop.domain.order.OrderRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final ItemRepository itemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository cutomerRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = cutomerRepository;
        this.itemRepository = itemRepository;
    }

    @Secured("ROLE_USER")
    @Transactional
    @Override
    public Order addOrder(String customerId, List<String> itemIds) {
        final Customer customer = customerRepository.findById(customerId);
        final List<Item> items = itemRepository.findByIds(itemIds);
        final List<OrderItem> orderItems = items.stream().map(OrderItem::new)
                .collect(Collectors.toList());

        Order order = Order.builder()
                .customer(customer)
                .orderItems(orderItems).build();

        orderRepository.save(order);
        return order;
    }

    @Transactional(readOnly = true)
    @Override
    public Order findOne(String orderId) {
        return orderRepository.findById(orderId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getOrdersForCustomer(String customerId) {
        return orderRepository.findAllByCustomreId(customerId);
    }
}
