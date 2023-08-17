package likelion.Spring_Like_Farmer.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import likelion.Spring_Like_Farmer.config.ResponseType;
import likelion.Spring_Like_Farmer.post.domain.Post;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
//    private Long postId;
//    private Long userId;
//    private String userImage;
//    private String userNickname;
//    private String userLocation;
//    private String image;
//    private String description;
//    private String comment;

    @Getter
    @Setter
    public static class SavePost {
        private String location;
        private String image;
        private String description;
        /*@Builder
        public SavePost(String location, String image, String description) {
            this.location = location;
            this.image = image;
            this.description = description;
        }*/
    }

//    @Getter
//    @Setter
//    public static class SavePostContent {
//        private String location;
//        private String description;
//    }


    @Getter
    public static class PostResponse extends ResponseType {

        @JsonInclude(NON_NULL)
        private Post post;

        public PostResponse(ExceptionCode exceptionCode) {
            super(exceptionCode);
        }
        public PostResponse(ExceptionCode exceptionCode, Post post) {
            super(exceptionCode);
            this.post = post;
        }
    }

    @Getter
    public static class PostListResponse extends ResponseType{
        List<Post> postList;
        public PostListResponse(ExceptionCode exceptionCode, List<Post> postList) {
            super(exceptionCode);
            this.postList = postList;
        }
    }
}
