package likelion.Spring_Like_Farmer.post.dto;

import likelion.Spring_Like_Farmer.post.domain.Post;
import likelion.Spring_Like_Farmer.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Long postId;
    private Long userId;
    private String userImage;
    private String userNickname;
    private String userLocation;
    private String image;
    private String description;
    private String comment;

    public static class CreatePost {
        public Long getUserId() {
            return userId;
        }

        public String getUserImage() {
            return userImage;
        }

        public String getUserNickname() {
            return userNickname;
        }

        public String getUserLocation() {
            return userLocation;
        }

        public String getImage() {
            return image;
        }

        public String getDescription() {
            return description;
        }

        public String getComment() {
            return comment;
        }

        private Long userId;
        private String userImage;
        private String userNickname;
        private String userLocation;
        private String image;
        private String description;
        private String comment;

        @Builder
        public CreatePost(Long userId, String userImage, String userNickname,
                          String userLocation, String image, String description, String comment) {
            this.userId = userId;
            this.userImage = userImage;
            this.userNickname = userNickname;
            this.userLocation = userLocation;
            this.image = image;
            this.description = description;
            this.comment = comment;
        }

    }

    public PostDto(Post post) {
        this.postId = post.getPostId();
        User user = post.getUser();
        if (user != null) {
            this.userId = user.getUserId();
            this.userImage = post.getUserImage();
            this.userNickname = post.getUserNickname();
            this.userLocation = post.getUserLocation();
        }
        this.image = post.getImage();
        this.description = post.getDescription();
        this.comment = post.getComment();
    }
}
