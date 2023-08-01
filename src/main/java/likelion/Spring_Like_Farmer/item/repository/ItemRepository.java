package likelion.Spring_Like_Farmer.item.repository;

import likelion.Spring_Like_Farmer.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item save(Item user);
    Optional<Item> findByItemId(Long userId);
    List<Item> findByUserUserId(Long userId);
    boolean existsByItemId(Long itemId);
}