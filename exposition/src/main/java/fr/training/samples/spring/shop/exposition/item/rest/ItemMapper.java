package fr.training.samples.spring.shop.exposition.item.rest;

import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.exposition.common.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper extends AbstractMapper<Item,ItemDto,ItemLightDto> {

    @Override
    public ItemDto mapToDto(final Item entity) {
        return new ItemDto(entity.getId(),entity.getDescription(), entity.getPrice());
    }

    @Override
    public Item mapToEntity(final ItemDto dto) {
        return Item.builder().id(dto.getItemID()).description(dto.getDescription()).price(dto.getPrice()).build();
    }

    @Override
    public Item mapLightDtoToEntity(ItemLightDto dto) {
        return Item.builder().description(dto.getDescription()).price(dto.getPrice()).build();
    }

}
