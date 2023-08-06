package likelion.Spring_Like_Farmer.item.controller;

import likelion.Spring_Like_Farmer.security.CurrentUser;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import likelion.Spring_Like_Farmer.item.dto.ItemDto;
import likelion.Spring_Like_Farmer.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping // 리스트
    public ResponseEntity<Object> saveItem(@CurrentUser UserPrincipal itemPrincipal,
                                           @RequestBody ItemDto.SaveItem saveItem) {
        return new ResponseEntity<>(itemService.saveItem(itemPrincipal, saveItem), HttpStatus.OK);
    }

    /*@GetMapping("/item/{itemId}") // 리스트
    public ResponseEntity<Object> findItems(@CurrentUser UserPrincipal itemPrincipal,
                                           @PathVariable Long itemId) {
        return new ResponseEntity<>(itemService.findItems(itemPrincipal, itemId), HttpStatus.OK);
    }*/

    @PatchMapping("/{itemId}") // 리스트
    public ResponseEntity<Object> updateItems(@CurrentUser UserPrincipal itemPrincipal,
                                              @PathVariable Long itemId,
                                              @RequestBody ItemDto.SaveItem saveItem) {
        return new ResponseEntity<>(itemService.updateItem(itemPrincipal, itemId, saveItem), HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}") // 리스트
    public ResponseEntity<Object> deleteItems(@CurrentUser UserPrincipal itemPrincipal,
                                           @PathVariable Long itemId) {
        return new ResponseEntity<>(itemService.deleteItem(itemPrincipal, itemId), HttpStatus.OK);
    }
}
