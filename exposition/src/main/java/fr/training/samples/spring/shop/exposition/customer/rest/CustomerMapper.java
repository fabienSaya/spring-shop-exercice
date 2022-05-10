package fr.training.samples.spring.shop.exposition.customer.rest;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.EmailAdress;
import fr.training.samples.spring.shop.domain.customer.PostalAddress;
import fr.training.samples.spring.shop.domain.customer.RoleTypeEnum;
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
        if (entity == null) {
            return null;
        }
        final CustomerDto dto = new CustomerDto();
        dto.setCustomerID(entity.getId());
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    @Override
    public Customer mapToEntity(final CustomerDto dto) {
        return Customer.builder()
                .id(dto.getCustomerID())
                .name(dto.getName())
                .password(dto.getPassword())
                .build();
    }


    @Override
    public Customer mapLightDtoToEntity(CustomerLightDto dto) {
        return Customer.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .addRole(RoleTypeEnum.ROLE_USER)//par default on donne un role user
                .email(EmailAdress.of(dto.getEmail()))
                .address(new PostalAddress(dto.getStreet(), dto.getCity(), dto.getCountry(), dto.getPostalCode()))
                .build();
    }


}
