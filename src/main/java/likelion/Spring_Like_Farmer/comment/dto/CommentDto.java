package likelion.Spring_Like_Farmer.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import likelion.Spring_Like_Farmer.comment.domain.Comment;
import likelion.Spring_Like_Farmer.config.ResponseType;
import likelion.Spring_Like_Farmer.post.domain.Post;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

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
    public static class SaveComment {

        private String nickname;
        private String password;
        private String content;
        private Long postId;

        public SaveComment(String nickname, String password, String content, Long postId) {
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
        private String password;
        public UpdateComment(String content) {
            this.content = content;
        }
    }

    @Getter
    public static class CommentResponse extends ResponseType {

        @JsonInclude(NON_NULL)
        private Comment comment;

        public CommentResponse(ExceptionCode exceptionCode) {
            super(exceptionCode);
        }
        public CommentResponse(ExceptionCode exceptionCode, Post post) {
            super(exceptionCode);
            this.comment = comment;
        }
    }

}
