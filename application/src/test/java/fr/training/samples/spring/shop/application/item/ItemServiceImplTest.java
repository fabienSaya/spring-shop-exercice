package fr.training.samples.spring.shop.application.item;

import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ItemServiceImpl.class })
public class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    public void addItem_should_call_save_repository_1_time() {
        final Item item = new Item();
        item.setDescription("item1");
        item.setPrice(1);

      //  when


    }

    @Test
    void getAllItems() {
    }
}