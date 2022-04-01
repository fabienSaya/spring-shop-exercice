package fr.training.samples.spring.shop.domain.item;

import java.util.List;

public interface ItemRepository {
    Item findById(String itemId);

    List<Item> findByIds(List<String> itemIds);

    void save(Item item);

    List<Item> findAll();
}
