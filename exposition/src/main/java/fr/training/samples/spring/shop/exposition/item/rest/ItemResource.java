package fr.training.samples.spring.shop.exposition.item.rest;

import fr.training.samples.spring.shop.application.item.ItemService;
import fr.training.samples.spring.shop.domain.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping
public class ItemResource {
    private static final Logger logger = LoggerFactory.getLogger(ItemResource.class);

    private final ItemService itemService;

    private final ItemMapper itemMapper;

    /**
     * Constructor for Bean injection
     */
    public ItemResource(final ItemService itemService, final ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @GetMapping(value = "/items", produces = { "application/json" })
    List getAllItemsUsingGet() {
         List<Item> items= itemService.getAllItems();
         logger.info("nb items returnes = "+items.size());
         return itemMapper.mapToDtoList(items);
    }
    /*

    addItemUsingPost(ItemLightDto)*/

}
