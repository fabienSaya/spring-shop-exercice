package fr.training.samples.spring.shop.exposition.customer.rest;

import fr.training.samples.spring.shop.application.customer.CustomerService;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.exposition.item.rest.ItemResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerResource {
    private static final Logger logger = LoggerFactory.getLogger(CustomerResource.class);

    private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    public CustomerResource(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping(value = "/customers", produces = { "application/json" })
    List getAllCustomersUsingGet() {
        List<Customer> customers= customerService.getAllCustomers();
        logger.info("nb Customer returned = "+customers.size());
        return customerMapper.mapToDtoList(customers);
    }

    @GetMapping(value = "/customers/{id}", produces = { "application/json" })
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable final String id) {
        logger.info("nlook for customer id = "+id);
        final Customer customer = customerService.findOne(id);
        CustomerDto customerDto= customerMapper.mapToDto(customer);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @PostMapping(value = "/customers", produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<?> addCustomerUsingPost(@Valid @RequestBody final CustomerLightDto customerLightDto) {

        final Customer customer = customerMapper.mapLightDtoToEntity(customerLightDto);

        customerService.create(customer);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(customer.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/customers", produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<URI> updateCustomerUsingPut(@Valid @RequestBody final CustomerDto CustomerDto) {

        final Customer customer = customerMapper.mapToEntity(CustomerDto);
        customerService.update(customer);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(customer.getId()).toUri();

        return ResponseEntity.created(location).build();
    }



}
