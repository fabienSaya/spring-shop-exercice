package fr.training.samples.spring.shop.exposition.item.rest;

import fr.training.samples.spring.shop.application.item.ItemService;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.exposition.common.ErrorModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api")
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


    @GetMapping(value = "/items/{id}", produces = { "application/json" })
    public ResponseEntity<ItemDto> getItemUsingGet(@PathVariable final String id) {

        final Item item = itemService.getItem(id);
/*
        return itemMapper.mapToDto(item);
 */
        ItemDto itemDto = itemMapper.mapToDto(item);
        return new ResponseEntity<>(itemDto, HttpStatus.OK);

    }

    @ApiOperation(value = "This operation allow to add a new item", nickname = "addItem", notes = "Please give item infos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Item was added"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found ", response = ErrorModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorModel.class) })
    @PostMapping(value = "/items", produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<URI> addItemUsingPost(@Valid @RequestBody final ItemLightDto itemDto) {

        final Item item = itemMapper.mapLightDtoToEntity(itemDto);
        itemService.addItem(item);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(item.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
