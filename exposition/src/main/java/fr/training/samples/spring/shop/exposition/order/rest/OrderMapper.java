package fr.training.samples.spring.shop.exposition.order.rest;

import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderItem;
import fr.training.samples.spring.shop.exposition.common.AbstractMapper;
import fr.training.samples.spring.shop.exposition.customer.rest.CustomerMapper;
import fr.training.samples.spring.shop.exposition.item.rest.ItemMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Mapper for the Order entity and its Dto .
 */
@Component
public class OrderMapper extends AbstractMapper<Order,OrderDto,OrderLightDto> {

    private final CustomerMapper customerMapper;

    private final ItemMapper itemMapper;

    public OrderMapper(final CustomerMapper customerMapper, final ItemMapper itemMapper) {
        this.customerMapper = customerMapper;
        this.itemMapper = itemMapper;
    }

    @Override
    public OrderDto mapToDto(final Order entity) {
        final OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setCustomer(customerMapper.mapToDto(entity.getCustomer()));

       // dto.setItems(itemMapper.mapToDtoList(entity.getOrderItems()));
        final List<OrderItem> orderItems = entity.getOrderItems();
        if (!CollectionUtils.isEmpty(orderItems)) {
            for (final OrderItem orderItem : orderItems) {
                dto.addItem(itemMapper.mapToDto(orderItem.getItem()));
            }
        }
        return dto;
    }

    @Override
    public Order mapToEntity(final OrderDto dto) {
       /* final Order order=new Order();
        order.setId(dto.getId());
        order.setCustomer(customerMapper.mapToEntity(dto.getCustomer()));
        order.setItems(itemMapper.mapToEntityList(dto.getItems()));
        order.setTotal(dto.getTotal());*/

        return null;
    }

    @Override
    public Order mapLightDtoToEntity(OrderLightDto dto) {

        return null;
    }
}