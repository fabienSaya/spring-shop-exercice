package fr.training.samples.spring.shop.application.order;

import fr.training.samples.spring.shop.domain.order.Order;

import java.util.List;

public interface OrderService {
    /**
     * Add new order according to the given customer id and items ids.
     *
     * @param CustomerId the customerId
     * @param itemIds    list of items ids
     * @return new order filled with the order SUM
     */
    public Order addOrder(String CustomerId, List<String> itemIds);

    /**
     * Get Order according to the given orderId
     *
     * @param orderId the order id
     * @return an Order
     */
    public Order findOne(String orderId);

    /**
     * Retrieve all orders for a customer according to the customer id.
     * @param customerId the customer id
     * @return a List of Orders
     */
    public List<Order> getOrdersForCustomer(String customerId);

}
