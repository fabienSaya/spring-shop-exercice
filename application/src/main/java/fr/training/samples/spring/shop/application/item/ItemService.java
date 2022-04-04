package fr.training.samples.spring.shop.application.item;

import fr.training.samples.spring.shop.domain.item.Item;

import java.util.List;

public interface ItemService {
    /**
     * Add Item to the catalog
     *
     * @param item the item to add
     * @return the new added item
     */
    Item addItem(Item item);

    /**
     * Display items catalog
     *
     * @return a list of item entities
     */
    List<Item> getAllItems();

    /**
     * Retrieve an item by his identifer
     *
     * @param id the item identifier
     * @return the found item
     */
    Item getItem(String id);

}


