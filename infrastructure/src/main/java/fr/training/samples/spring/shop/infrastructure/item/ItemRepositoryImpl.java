package fr.training.samples.spring.shop.infrastructure.item;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    ItemJpaRepository itemJpaRepository;

    public ItemRepositoryImpl(ItemJpaRepository itemJpaRepository) {
        this.itemJpaRepository = itemJpaRepository;
    }

    @Override
    public Item findById(String itemId) {
       // return itemJpaRepository.findById(itemId);
        return itemJpaRepository.findById(itemId).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void save(Item item) {
        itemJpaRepository.save(item);
    }

    @Override
    public List<Item> findAll() {
        return itemJpaRepository.findAll();
    }
}
