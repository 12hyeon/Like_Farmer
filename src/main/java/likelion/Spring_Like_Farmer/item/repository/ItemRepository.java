package likelion.Spring_Like_Farmer.item.repository;

import likelion.Spring_Like_Farmer.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item save(Item item);
    Optional<Item> findByItemId(Long itemId);
    List<Item> findAllByUserUserId(Long userUserId);
    boolean existsByItemId(Long itemId);
}