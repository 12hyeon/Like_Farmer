package likelion.Spring_Like_Farmer.item.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import likelion.Spring_Like_Farmer.item.domain.Item;
import likelion.Spring_Like_Farmer.config.ResponseType;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.Data;
import lombok.Getter;
import java.util.List;

public class ItemDto {

    @Getter
    public static class SaveItem {
        String title;
        int gram;
    }

    @Getter
    public static class UpdateItem {
        String nickname;
        String location;
        String description;
        List<Item> items;
    }

    @Getter
    public static class ItemResponse extends ResponseType {

        public ItemResponse(ExceptionCode exceptionCode) {
            super(exceptionCode);
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
