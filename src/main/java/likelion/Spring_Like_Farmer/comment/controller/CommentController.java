package likelion.Spring_Like_Farmer.comment.controller;

import likelion.Spring_Like_Farmer.comment.dto.CommentDto;
import likelion.Spring_Like_Farmer.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 달기
    @PostMapping("/create")
    public Object createComment(@RequestBody CommentDto.CreateComment request) {
        return commentService.createComment(request);
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
}
