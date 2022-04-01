package fr.training.samples.spring.shop.infrastructure.item;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class) //active spring
@DataJpaTest//active les tests JPA et va lancer une base h2 en memoire
public class ItemRepositoryImplTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void findById_existing_item_id_should_succeed() {
        //INSERT INTO ITEM (ID, DESCRIPTION, PRICE,VERSION) VALUES ('123e4567-e89b-42d3-a456-556642440001', 'DESC1', 10,0);
        final String itemId = "123e4567-e89b-42d3-a456-556642440001";

        Item item = itemRepository.findById(itemId);
        assertThat(item).isNotNull();
        assertThat(item.getId()).isEqualTo(itemId);
        assertThat(item.getDescription()).isEqualTo("DESC1");
        assertThat(item.getPrice()).isEqualTo(10);

    }

    @Test
    public void save_new_item_should_succeed() {
        final String itemID="idfab";

        Item item=new Item();
        item.setId(itemID);
        item.setDescription("Item test");
        item.setPrice(25);


        itemRepository.save(item);

        Item savedItem= itemRepository.findById(itemID);
        assertThat(savedItem).isNotNull();
        assertThat(item).isEqualTo(savedItem);//on a surchargé equals de Item pour que ca marche bien
    }

    @Test(expected = NotFoundException.class)
    public void findById_unknown_item_id_should_fail() {
        // Given
        final String idItem = "dummy";

        // When
        itemRepository.findById(idItem);

        // Then
    }

    @Test
    public void findAll_should_return_5_elements() {
        List<Item> result = itemRepository.findAll();

        assertThat(result).hasSize(5);
    }




/*
    @Test
    public void save_2_customers_with_same_id_should_fail() {
        final String customerID="idfab";

        Customer customer=new Customer();
        customer.setId(customerID);
        customer.setName("fabien");
        customer.setPassword("xxxx");
        customer.setVersion(0);

        Customer customer2=new Customer();
        customer2.setId(customerID);
        customer2.setName("fabien encore");
        customer2.setPassword("yyyy");
        customer2.setVersion(0);

        customerRepository.save(customer);
        customerRepository.save(customer2);

    }

 */



}