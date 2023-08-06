package likelion.Spring_Like_Farmer.item.service;

import likelion.Spring_Like_Farmer.item.domain.Item;
import likelion.Spring_Like_Farmer.item.dto.ItemDto;
import likelion.Spring_Like_Farmer.item.repository.ItemRepository;
import likelion.Spring_Like_Farmer.item.domain.Item;
import likelion.Spring_Like_Farmer.item.dto.ItemDto;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import likelion.Spring_Like_Farmer.user.domain.User;
import likelion.Spring_Like_Farmer.user.repository.UserRepository;
import likelion.Spring_Like_Farmer.validation.CustomException;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public Object saveItem(UserPrincipal itemPrincipal, ItemDto.SaveItem saveItem) {

        User user = userRepository.findByUserId(itemPrincipal.getUserId()).get();

        Item item = Item.builder()
                .saveItem(saveItem)
                .user(user)
                .build();
        itemRepository.save(item);

        return new ItemDto.ItemResponse(ExceptionCode.ITEM_SAVE_OK);
    }

    public Object findItems(UserPrincipal itemPrincipal, Long itemId) {

        List<Item> items = itemRepository.findAllByUserUserId(itemPrincipal.getUserId());
        return new ItemDto.ItemResponse(ExceptionCode.ITEM_GET_OK, items);
    }

    public Object updateItem(UserPrincipal itemPrincipal, Long itemId, ItemDto.SaveItem saveItem) {

        Item item = itemRepository.findByItemId(itemId)
                .orElseThrow(() -> new CustomException(ExceptionCode.ITEM_NOT_FOUND));


        item.updateItem(saveItem);
        itemRepository.save(item);

        return new ItemDto.ItemResponse(ExceptionCode.ITEM_UPDATE_OK);
    }

    public Object deleteItem(UserPrincipal itemPrincipal, Long itemId) {

        Item item = itemRepository.findByItemId(itemId)
                .orElseThrow(() -> new CustomException(ExceptionCode.ITEM_NOT_FOUND));



        itemRepository.delete(item);
        
        return new ItemDto.ItemResponse(ExceptionCode.ITEM_DELETE_OK);
    }
}
