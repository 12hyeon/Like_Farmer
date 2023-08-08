package likelion.Spring_Like_Farmer.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private Long commentId;
    private String nickname;
    private String content;
    private Long postId;

    @Getter
    @Setter
    public static class CreateComment {

        private String nickname;
        private String password;
        private String content;
        private Long postId;

        public CreateComment(String nickname, String password, String content, Long postId) {
            this.nickname = nickname;
            this.password = password;
            this.content = content;
            this.postId = postId;
        }
    }

    @Getter
    @Setter
    public static class UpdateComment {
        private String content;
        public UpdateComment(String content) {
            this.content = content;
        }
    }


}
