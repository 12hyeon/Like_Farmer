package likelion.Spring_Like_Farmer.item.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import likelion.Spring_Like_Farmer.config.ResponseType;
import likelion.Spring_Like_Farmer.item.domain.Item;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class ItemDto {

    @Getter
    public static class SaveItem {
        String title;
        int gram;
    }

    @Getter
    public static class UpdateItem {
        int itemId;
        String title;
        int gram;
    }

    @Getter
    public static class ItemResponse extends ResponseType {

        @JsonInclude(NON_NULL)
        private List<Item> items;

        public ItemResponse(ExceptionCode exceptionCode) {
            super(exceptionCode);
        }

        public ItemResponse(ExceptionCode exceptionCode, List<Item> items) {
            super(exceptionCode);
            this.items = items;
        }
    }

    @Getter
    public static class ItemListResponse extends ResponseType{

        List<Item> itemList;
        int total;

        public ItemListResponse(ExceptionCode exceptionCode, List<Item> itemList) {
            super(exceptionCode);
            this.total = itemList.size();
            this.itemList = itemList;
        }
    }
}
