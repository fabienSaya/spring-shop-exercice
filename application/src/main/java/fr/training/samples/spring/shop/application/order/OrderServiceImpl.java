package fr.training.samples.spring.shop.application.order;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final ItemRepository itemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository cutomerRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = cutomerRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    @Override
    public Order addOrder(String customerId, List<String> itemIds) {
        Customer customer= customerRepository.findById(customerId);

        Order order = new Order();
        order.setCustomer(customer);

        /* perso, il faudrait mieux que le Total soit calculé dans addItem de order plutot
        que faire un setTotal. On encapsule pas le calcul du coup
         */

        Integer total=0;
        for (String itemId: itemIds) {
            Item item= itemRepository.findById(itemId);
            order.addItem(item);
            total+=item.getPrice();
        }
        order.setTotal(total);
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
