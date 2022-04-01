package fr.training.samples.spring.shop.exposition.item.rest;

import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.exposition.common.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper extends AbstractMapper<ItemDto,Item> {
    @Override
    public ItemDto mapToDto(final Item entity) {
        return new ItemDto(entity.getId(),entity.getDescription(), entity.getPrice());
    }

    @Override
    public Item mapToEntity(final ItemDto dto) {
        Item item= new Item();
        item.setId(dto.getItemID());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        return item;
    }

    public Item mapToEntity(final ItemLightDto dto) {
        final Item entity = new Item();
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        return entity;
    }
}
