package likelion.Spring_Like_Farmer.item.service;

import likelion.Spring_Like_Farmer.item.dto.ItemDto;
import likelion.Spring_Like_Farmer.item.repository.ItemRepository;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    
    private final ItemRepository itemRepository;

    public Object saveItem(UserPrincipal userPrincipal, ItemDto.SaveItem saveItem) {
        return new ItemDto.ItemResponse(ExceptionCode.EMPTY);
    }

    public Object findItems(UserPrincipal userPrincipal, Long userId) {
        return new ItemDto.ItemResponse(ExceptionCode.EMPTY);
    }

    public Object updateItem(UserPrincipal userPrincipal, ItemDto.SaveItem saveItem) {
        return new ItemDto.ItemResponse(ExceptionCode.EMPTY);
    }

    public Object deleteItem(UserPrincipal userPrincipal, Long itemId) {
        return new ItemDto.ItemResponse(ExceptionCode.EMPTY);
    }
}
