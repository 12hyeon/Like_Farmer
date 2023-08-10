package likelion.Spring_Like_Farmer.comment.controller;

import likelion.Spring_Like_Farmer.comment.dto.CommentDto;
import likelion.Spring_Like_Farmer.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 달기
    @PostMapping("/save")
    public Object createComment(@RequestBody CommentDto.SaveComment saveComment) {
        return commentService.createComment(saveComment);
    }

    // 댓글 수정하기
    @PutMapping("/update/{commentId}")
    public Object updateComment(@PathVariable Long commentId,
                              @RequestBody CommentDto.UpdateComment request) {
        return commentService.updateComment(commentId, request);
    }

    // 댓글 삭제하기
    @DeleteMapping("/delete/{commentId}")
    public Object deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }

    // 댓글 전체 불러오기
    @GetMapping("/list/{postId}")
    public List<CommentDto> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }

}
