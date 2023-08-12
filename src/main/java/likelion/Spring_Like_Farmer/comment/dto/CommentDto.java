package likelion.Spring_Like_Farmer.comment.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import likelion.Spring_Like_Farmer.comment.domain.Comment;
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
    public static class CommentResponse extends ResponseType {
        @JsonInclude(NON_NULL)
        private Comment comment;

        private String message;

        public CommentResponse(ExceptionCode exceptionCode) {
            super(exceptionCode);
        }
        private List<CommentDto> commentList;

        public CommentResponse(ExceptionCode exceptionCode, List<CommentDto> commentList, String message) { // 저장에 실패했을때 메시지와 함께 반환
            super(exceptionCode);
            this.commentList = commentList;
            this.message = message;
        }
//        public CommentResponse(ExceptionCode exceptionCode, List<CommentDto> commentList) {
//            super(exceptionCode);
//            this.commentList = commentList;
//        }
        public CommentResponse(ExceptionCode exceptionCode, String message) {
            super(exceptionCode);
            this.message = message;
        }
    }
}