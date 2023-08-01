package likelion.Spring_Like_Farmer.item.controller;

import likelion.Spring_Like_Farmer.security.CurrentUser;
import likelion.Spring_Like_Farmer.item.dto.ItemDto;
import likelion.Spring_Like_Farmer.item.service.ItemService;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/item/{userId}") // 리스트
    public ResponseEntity<Object> saveItem(@CurrentUser UserPrincipal userPrincipal,
                                           @RequestBody ItemDto.SaveItem saveItem) {
        return new ResponseEntity<>(itemService.saveItem(userPrincipal, saveItem), HttpStatus.OK);
    }

    @GetMapping("/item/{userId}") // 리스트
    public ResponseEntity<Object> findItems(@CurrentUser UserPrincipal userPrincipal,
                                           @PathVariable Long userId) {
        return new ResponseEntity<>(itemService.findItems(userPrincipal, userId), HttpStatus.OK);
    }

    @PatchMapping("/item/{itemId}") // 리스트
    public ResponseEntity<Object> updateItems(@CurrentUser UserPrincipal userPrincipal,
                                           @PathVariable ItemDto.SaveItem saveItem) {
        return new ResponseEntity<>(itemService.updateItem(userPrincipal, saveItem), HttpStatus.OK);
    }

    @DeleteMapping("/item/{itemId}") // 리스트
    public ResponseEntity<Object> deleteItems(@CurrentUser UserPrincipal userPrincipal,
                                           @PathVariable Long itemId) {
        return new ResponseEntity<>(itemService.deleteItem(userPrincipal, itemId), HttpStatus.OK);
    }
}
