package likelion.Spring_Like_Farmer.user.dto;

import lombok.Data;

public class ImageDto {
    @Data
    public static class ImageUrlResponse {
        String profileImageUrl;

        public ImageUrlResponse(String profileImageUrl){
            this.profileImageUrl = profileImageUrl;
        }

    }
}
