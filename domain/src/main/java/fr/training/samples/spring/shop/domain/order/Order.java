package fr.training.samples.spring.shop.domain.order;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ORDERS")
public class Order extends AbstractBaseEntity {
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    //target entity me semble inutile car dans la list on precise que c'est un item. Les forums confirment ce point
    @ManyToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
    private List<Item> items=new ArrayList<>();

    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(getCustomer(), order.getCustomer()) && Objects.equals(getItems(), order.getItems()) && Objects.equals(getTotal(), order.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCustomer(), getItems(), getTotal());
    }
}
