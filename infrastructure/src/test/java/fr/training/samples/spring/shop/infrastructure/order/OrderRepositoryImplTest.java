package fr.training.samples.spring.shop.infrastructure.order;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class) //active spring
@DataJpaTest//active les tests JPA et va lancer une base h2 en memoire
public class OrderRepositoryImplTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;




    @Test
    public void findById_on_existing_order_id_should_succeed() {
        //INSERT INTO ORDERS (ID, CUSTOMER_ID, TOTAL, VERSION) VALUES ('123e4567-189b-42d3-a456-556642440000','123e4567-e89b-42d3-a456-556642440000',30,0);
        //INSERT INTO ORDERS_ITEMS (ITEMS_ID, ORDER_ID) VALUES ('123e4567-e89b-42d3-a456-556642440001','123e4567-189b-42d3-a456-556642440000');
        //INSERT INTO ORDERS_ITEMS (ITEMS_ID, ORDER_ID) VALUES ('123e4567-e89b-42d3-a456-556642440002','123e4567-189b-42d3-a456-556642440000');

        // Given existing order in db
        final String orderId = "123e4567-189b-42d3-a456-556642440000";

        // When
        final Order order = orderRepository.findById(orderId);

        // Then
        assertThat(order).isNotNull();
        assertThat(order.getId()).isEqualTo(orderId);
        assertThat(order.getCustomer().getName()).isEqualTo("NAME1");
        assertThat(order.getItems()).hasSize(2);


    }

    @Test
    public void save_new_order_should_succeed() {
        final String orderId = "newOrder";

        // Given
        final Order order = new Order();
        order.setId(orderId);

        final Customer customer = customerRepository.findById("123e4567-e89b-42d3-a456-556642440000");
        order.setCustomer(customer);
        final Item item = itemRepository.findById("123e4567-e89b-42d3-a456-556642440005");
        order.addItem(item);
        // When
        orderRepository.save(order);
        // Then
        assertThat(orderRepository.findById(order.getId())).isNotNull();


        Order savedOrder= orderRepository.findById(orderId);
        assertThat(savedOrder).isNotNull();
        assertThat(order).isEqualTo(savedOrder);//on a surchargé equals de Item pour que ca marche bien

        assertThat(savedOrder.getItems()).size().isEqualTo(1);
    }

    @Test(expected = NotFoundException.class)
    public void findById_on_unknown_customer_id_should_fail() {
        final String idOrder = "unknown";
        orderRepository.findById(idOrder);
    }
}