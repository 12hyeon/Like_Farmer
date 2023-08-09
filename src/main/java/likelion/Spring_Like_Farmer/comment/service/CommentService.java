package likelion.Spring_Like_Farmer.comment.service;

import likelion.Spring_Like_Farmer.comment.domain.Comment;
import likelion.Spring_Like_Farmer.comment.dto.CommentDto;
import likelion.Spring_Like_Farmer.comment.repository.CommentRepository;
import likelion.Spring_Like_Farmer.post.domain.Post;
import likelion.Spring_Like_Farmer.post.repository.PostRepository;
import likelion.Spring_Like_Farmer.validation.CustomException;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public Object createComment(CommentDto.CreateComment request) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new CustomException(ExceptionCode.COMMENT_NOT_FOUND));
        Comment comment = new Comment(request.getNickname(), request.getPassword(), request.getContent(), post);
        commentRepository.save(comment);
        return new CommentDto.CommentResponse(ExceptionCode.COMMENT_SAVE_OK);
    }

    public Object updateComment(Long commentId, CommentDto.UpdateComment request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ExceptionCode.COMMENT_NOT_FOUND));
        comment.setContent(request.getContent());
        return new CommentDto.CommentResponse(ExceptionCode.COMMENT_UPDATE_OK);
    }

    public Object deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ExceptionCode.COMMENT_NOT_FOUND));
        commentRepository.delete(comment);
        return new CommentDto.CommentResponse(ExceptionCode.COMMENT_DELETE_OK);
    }
}
