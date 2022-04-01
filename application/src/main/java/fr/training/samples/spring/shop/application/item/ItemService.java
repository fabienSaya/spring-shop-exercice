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
    public Item addItem(Item item);

    /**
     * Display items catalog
     *
     * @return a list of item entities
     */
    public List<Item> getAllItems();

}


