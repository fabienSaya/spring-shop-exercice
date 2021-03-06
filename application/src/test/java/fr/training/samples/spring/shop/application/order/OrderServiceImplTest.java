package fr.training.samples.spring.shop.application.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.training.samples.spring.shop.domain.order.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderRepository;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { OrderServiceImpl.class })
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private  OrderRepository orderRepositoryMock;

    @MockBean
    private  CustomerRepository customerRepositoryMock;

    @MockBean
    private  ItemRepository itemRepositoryMock;

    private Order getNewOrder() {
        final List<OrderItem> orderItems = getItems().stream().map(OrderItem::new)
                .collect(Collectors.toList());
        return Order.builder().customer(getCustomer()).orderItems(orderItems).build();
    }

    private List<Item> getItems() {
        final List<Item> items = new ArrayList<>();
        items.add(Item.builder().description("item 1").price(1).build());
        items.add(Item.builder().description("item 2").price(2).build());
        items.add(Item.builder().description("item 3").price(3).build());
        return items;
    }

    private Customer getCustomer() {
        final Customer customer = Customer.builder()
                .name("Michel Dupont")
                .password("password").build();
        return customer;
    }


    @Test
    public void addOrder_should_call_save_repositories_1_time() {
        //given
        final String customerId = "123e4567-e89b-42d3-a456-556642440000";
        final List<String> itemIds = new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            itemIds.add(String.valueOf(i));
        }
        final List<Item> items=getItems();

        when(customerRepositoryMock.findById(customerId)).thenReturn(getCustomer());
        /*when(itemRepositoryMock.findById(itemIds.get(0))).thenReturn(items.get(0));
        when(itemRepositoryMock.findById(itemIds.get(1))).thenReturn(items.get(1));
        when(itemRepositoryMock.findById(itemIds.get(2))).thenReturn(items.get(2));

         */

        when(itemRepositoryMock.findByIds(itemIds)).thenReturn(items);

        // En fait, il ne faut pas mocker la partie "save". Comme c'est un MockBean, il ne va pas faire d'insertion en base

        //when
        Order order= orderService.addOrder(customerId,itemIds);

        assertThat(order).isNotNull();
        assertThat(order.getTotal()).isEqualTo(6);
        //permet de v??rifier si
        verify(orderRepositoryMock, times(1)).save(any());
        verify(customerRepositoryMock, times(1)).findById(customerId);
        //verify(itemRepositoryMock, times(3)).findById(any());
        when(itemRepositoryMock.findByIds(itemIds)).thenReturn(items);

    }

    @Test
    public void findOne_should_call_findById_repository_1_time() {
        // Given
        final String orderId = "1234567";
        final Order order = getNewOrder();
        when(orderRepositoryMock.findById(orderId)).thenReturn(order);

        //When
        Order orderFound=orderService.findOne(orderId);

        //then
        assertThat(orderFound).isNotNull();
        assertThat(orderFound.getCustomer().getName()).isEqualTo("Michel Dupont");
        assertThat(orderFound.getCustomer().getPassword()).isEqualTo("password");
        verify(orderRepositoryMock, times(1)).findById(orderId);

    }

    @Test
    public void getOrdersForCustomer_should_return_orders() {
        // Given
        final String customerId = "123e4567-e89b-42d3-a456-556642440000";
        final List<Order> orders = new ArrayList<>();
        orders.add(getNewOrder());

        when(orderRepositoryMock.findAllByCustomreId(customerId)).thenReturn(orders);

        //when
        List<Order> ordersFound= orderService.getOrdersForCustomer(customerId);

        //then
        assertThat(ordersFound).hasSize(1);

    }
}