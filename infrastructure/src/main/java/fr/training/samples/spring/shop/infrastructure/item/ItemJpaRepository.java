package fr.training.samples.spring.shop.infrastructure.item;

import fr.training.samples.spring.shop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<Item,String> {
}
