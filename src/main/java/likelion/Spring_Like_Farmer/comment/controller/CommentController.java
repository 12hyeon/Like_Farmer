package likelion.Spring_Like_Farmer.comment.controller;

import likelion.Spring_Like_Farmer.comment.dto.CommentDto;
import likelion.Spring_Like_Farmer.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/auth/comment")
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
//    @PatchMapping("/{postId}/{commentId}")
//    public ResponseEntity<List<CommentDto>> updateComment(@PathVariable Long postId, @PathVariable Long commentId,
//                                                          @RequestBody CommentDto.SaveComment saveComment) {
//        commentService.updateComment(postId, commentId, saveComment.getPassword(), saveComment);
//        return ResponseEntity.ok(commentService.getComments(postId));
//    }
    @PatchMapping("/{postId}/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long postId, @PathVariable Long commentId,
                                           @RequestBody CommentDto.SaveComment saveComment) {
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, saveComment.getPassword(), saveComment));
    }

    // 댓글 삭제하기 및 전체 댓글 불러오기
//    @DeleteMapping("/{postId}/{commentId}")
//    public ResponseEntity<List<CommentDto>> deleteComment(@PathVariable Long postId,
//                                                          @PathVariable Long commentId, @RequestBody CommentDto.SaveComment deleteComment) {
//        commentService.deleteComment(postId, commentId, deleteComment.getPassword());
//        return ResponseEntity.ok(commentService.getComments(postId));
//    }
    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long postId,
                                           @PathVariable Long commentId, @RequestBody CommentDto.SaveComment deleteComment) {
        return ResponseEntity.ok(commentService.deleteComment(postId, commentId, deleteComment.getPassword()));
    }
    // 댓글 전체 불러오기
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getComments(postId));
    }
}