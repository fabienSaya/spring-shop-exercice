package fr.training.samples.spring.shop.infrastructure.customer;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * on utilise junit4 d'apres la correction du TP.
 *
 * avec @DataJpaTest
 * il crée et initialise une base h2 en memoire avec ce qu'il y a dans le fichier resource import.sql
 */

@RunWith(SpringRunner.class) //active spring
@DataJpaTest//active les tests JPA et va lancer une base h2 en memoire
public class CustomerRepositoryImplTest {


    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findById_existing_customer_id_should_succeed() {
        //INSERT INTO CUSTOMER (ID, NAME, PASSWORD,VERSION) VALUES ('123e4567-e89b-42d3-a456-556642440000', 'NAME1', 'PASS1',0);

        Customer customer = customerRepository.findById("123e4567-e89b-42d3-a456-556642440000");
        assertThat(customer).isNotNull();
        assertThat(customer.getName()).isEqualTo("NAME1");
        assertThat(customer.getPassword()).isEqualTo("PASS1");
        assertThat(customer.getVersion()).isEqualTo(0);

    }

    @Test
    public void save_new_customer_should_succeed() {
        final String customerID="idfab";

        Customer customer=new Customer();
        customer.setId(customerID);
        customer.setName("fabien");
        customer.setPassword("xxxx");
        customer.setVersion(0);

        customerRepository.save(customer);

        Customer savedCustomer= customerRepository.findById(customerID);
        assertThat(savedCustomer).isNotNull();
        assertThat(customer.getName()).isEqualTo(savedCustomer.getName());
    }

    @Test(expected = NotFoundException.class)
    public void findById_unknown_customer_id_should_fail() {
        final String idCustomer = "unknown";
        customerRepository.findById(idCustomer);
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