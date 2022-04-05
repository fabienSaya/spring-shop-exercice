package fr.training.samples.spring.shop.domain.order;

import java.util.ArrayList;
import java.util.List;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.item.Item;
import org.apache.commons.lang3.Validate;

public class Order extends AbstractBaseEntity {

    private Customer customer;

    private final List<OrderItem> orderItems  = new ArrayList<>();

    private Integer total = 0;

    /**
     * No-arg constructor for JavaBean tools
     */
    Order() {

    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Integer getTotal() {
        return total;
    }

    public void addOrderItem(final OrderItem orderItem) {
        orderItems.add(orderItem);
        total = Integer.sum(total, orderItem.getItem().getPrice() * orderItem.getQuantity());
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", orderItems=" + orderItems +
                ", total=" + total +
                '}';
    }

    /**
     * private constructor to enforce Builder usage
     */
    private Order(final Builder builder) {
        if (builder.id != null) {
            id = builder.id;
        }
        customer = builder.customer;
        total = 0;
        for (final OrderItem item : builder.orderItems) {
            addOrderItem(item);
        }
    }

    /**
     * Builder static assessor
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder pattern to ensure Order is immutable.
     */
    public static class Builder {
        private String id;
        private Customer customer;
        private List<OrderItem> orderItems = new ArrayList<>();

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder customer(final Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder addOrderItem(final OrderItem orderItem) {
            orderItems.add(orderItem);
            return this;
        }

        public Builder orderItems(final List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public Order build() {
            Validate.notNull(customer, "Customer is required");
            Validate.isTrue(!orderItems.isEmpty(), "Order must have one item at least");
            return new Order(this);
        }

    }
}