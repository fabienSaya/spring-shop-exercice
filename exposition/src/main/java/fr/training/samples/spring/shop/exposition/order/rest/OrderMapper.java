package fr.training.samples.spring.shop.exposition.order.rest;

import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.exposition.common.AbstractMapper;
import fr.training.samples.spring.shop.exposition.customer.rest.CustomerMapper;
import fr.training.samples.spring.shop.exposition.item.rest.ItemMapper;
import org.springframework.stereotype.Component;

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
        dto.setItems(itemMapper.mapToDtoList(entity.getItems()));
        dto.setTotal(entity.getTotal());
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