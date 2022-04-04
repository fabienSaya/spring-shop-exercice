package fr.training.samples.spring.shop.exposition.order.rest;

import fr.training.samples.spring.shop.application.order.OrderService;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.exposition.item.rest.ItemResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderResource {

    private static final Logger logger = LoggerFactory.getLogger(OrderResource.class);

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    public OrderResource(final OrderService orderService, final OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping(value = "/orders", consumes = { "application/json" })
    public ResponseEntity<URI> addOrderUsingPost(@RequestBody final OrderLightDto orderLightDto) {

        logger.info("order to create: "+orderLightDto);
        final Order order = orderService.addOrder(orderLightDto.getCustomerId(), orderLightDto.getItemIds());

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * En doublon avec la méthode CustomerResource.getOrders(String)
     */
    @GetMapping(value = "/orders", produces = { "application/json" })
    public ResponseEntity<List<OrderDto>> retrieveOrdersByCustomer(@RequestParam final String customerId) {
        final List<Order> orders = orderService.getOrdersForCustomer(customerId);

        return new ResponseEntity<>(orderMapper.mapToDtoList(orders), HttpStatus.OK);
    }

}
