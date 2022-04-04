package fr.training.samples.spring.shop.exposition.customer.rest;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.exposition.common.AbstractMapper;
import org.springframework.stereotype.Component;

import javax.validation.Valid;


/**
 *  Mapper for the entity Customer and its DTO CustomeDTO.
 */
@Component
public class CustomerMapper extends AbstractMapper<Customer,CustomerDto,CustomerLightDto> {

    @Override
    public CustomerDto mapToDto(final Customer entity) {
        final CustomerDto dto = new CustomerDto();
        dto.setCustomerID(entity.getId());
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    @Override
    public Customer mapToEntity(final CustomerDto dto) {
        final Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setPassword(dto.getPassword());
        customer.setId(dto.getCustomerID());
        return customer;
    }


    @Override
    public Customer mapLightDtoToEntity(CustomerLightDto dto) {
        final Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setPassword(dto.getPassword());
        return customer;
    }

}
