package fr.training.samples.spring.shop.application.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import fr.training.samples.spring.shop.domain.customer.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import fr.training.samples.spring.shop.domain.common.exception.AlreadyExistingException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CustomerServiceImpl.class})
public class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepositoryMock;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private Customer getDefaultCustomer() {
        return Customer.builder()
                .name("name")
                .password("password")
                .addRole(RoleTypeEnum.ROLE_USER)
                .email(EmailAdress.of("michel.dupont@gmail.com")) //
                .address(new PostalAddress("10 main street", "Las Vegas", "Eldorado", "123456"))
                .build();
    }

    @Test
    public void createCustomer_should_success_when_not_already_exist() {
        // Given
        when(passwordEncoder.encode("password")).thenReturn("password");
        Customer customer = getDefaultCustomer();

        when(customerRepositoryMock.findByCustomerName("name")).thenReturn(null);

        // When
        final Customer result = customerService.create(customer);

        // Then
        assertThat(result).isNotNull();
        verify(customerRepositoryMock, times(1)).save(customer);
    }

    @Test
    public void createCustomer_should_fail_when_already_exist() {
        // Given
        Customer customer = getDefaultCustomer();

        when(customerRepositoryMock.findByCustomerName("name")).thenReturn(customer);

        // When
        Customer result;
        try {
            result = customerService.create(customer);
        } catch (final Exception e) {
            assertThat(e).isInstanceOf(AlreadyExistingException.class);
        }

        // Then
        verify(customerRepositoryMock, never()).save(customer);
    }

    @Test
    public void findOne_should_call_findById_repository_1_time() {
        // Given
        final String customerId = "123e4567-e89b-42d3-a456-556642440000";
        /*final Customer customer = new Customer();
        customer.setName("Michel Dupont");
        customer.setPassword("password");*/

        Customer customer = getDefaultCustomer();

        when(customerRepositoryMock.findById(customerId)).thenReturn(customer);

        // When
        final Customer result = customerService.findOne(customerId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("name");
        assertThat(result.getPassword()).isEqualTo("password");
    }

   /* @Test
    public void update_should_call_save_repository_1_time() {
        // Given
        final Customer customer = Customer.builder() //
                .name("Michel Dupont") //
                .password(Password.of("password"))
                .email(EmailAdress.of("michel.dupont@gmail.com")) //
                .address(new PostalAddress("10 main street", "Las Vegas", "Eldorado", "123456"))
                .build();

        // When
        final Customer result = customerService.create(customer);

        // Then
        assertThat(result).isNotNull();
        verify(customerRepositoryMock, times(1)).save(customer);
    }*/


}