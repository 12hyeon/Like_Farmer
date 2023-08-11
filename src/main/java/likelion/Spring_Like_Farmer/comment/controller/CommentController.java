package likelion.Spring_Like_Farmer.comment.controller;

import likelion.Spring_Like_Farmer.comment.dto.CommentDto;
import likelion.Spring_Like_Farmer.comment.service.CommentService;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    // 댓글 달기 및 전체 댓글 불러오기
    @PostMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> saveComment(@PathVariable Long postId,
                                                        @RequestBody CommentDto.SaveComment saveComment) {
        commentService.saveComment(postId, saveComment);
        return ResponseEntity.ok(commentService.getComments(postId));
    }

    // 댓글 수정하기 및 전체 댓글 불러오기
    @PatchMapping("/{postId}/{commentId}")
    public ResponseEntity<List<CommentDto>> updateComment(@PathVariable Long postId, @PathVariable Long commentId,
                                                          @RequestParam String password, @RequestBody CommentDto.UpdateComment updateComment) {
        ExceptionCode responseCode = (ExceptionCode) commentService.updateComment(postId, commentId, password, updateComment);
        if(responseCode == ExceptionCode.COMMENT_UPDATE_OK) {
            return ResponseEntity.ok(commentService.getComments(postId));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // 댓글 삭제하기 및 전체 댓글 불러오기
    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<List<CommentDto>> deleteComment(@PathVariable Long postId,
                                                          @PathVariable Long commentId, @RequestParam String password) {
        ExceptionCode responseCode = (ExceptionCode) commentService.deleteComment(postId, commentId, password);
        if(responseCode == ExceptionCode.COMMENT_DELETE_OK) {
            return ResponseEntity.ok(commentService.getComments(postId));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    // 댓글 전체 불러오기
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getComments(postId));
    }
}