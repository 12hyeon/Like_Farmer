package likelion.Spring_Like_Farmer.comment.service;

import likelion.Spring_Like_Farmer.comment.domain.Comment;
import likelion.Spring_Like_Farmer.comment.dto.CommentDto;
import likelion.Spring_Like_Farmer.comment.repository.CommentRepository;
import likelion.Spring_Like_Farmer.item.dto.ItemDto;
import likelion.Spring_Like_Farmer.post.domain.Post;
import likelion.Spring_Like_Farmer.post.repository.PostRepository;
import likelion.Spring_Like_Farmer.validation.CustomException;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public Object saveComment(CommentDto.SaveComment saveComment) {
        Post post = postRepository.findById(saveComment.getPostId())
                .orElseThrow(() -> new CustomException(ExceptionCode.COMMENT_NOT_FOUND));
        Comment comment = new Comment(saveComment.getNickname(), saveComment.getPassword(), saveComment.getContent(), post);
        commentRepository.save(comment);
        return new CommentDto.CommentResponse(ExceptionCode.COMMENT_SAVE_OK);
    }

    public Object updateComment(Long commentId, CommentDto.UpdateComment saveComment) {
        Optional<Comment> findComment = commentRepository.findByCommentId(commentId);

        if (findComment.isPresent() && findComment.get().getPassword().equals(saveComment.getPassword())) {
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new CustomException(ExceptionCode.COMMENT_NOT_FOUND));
            comment.setContent(saveComment.getContent());
            return new CommentDto.CommentResponse(ExceptionCode.COMMENT_UPDATE_OK);
        } else {
            return new ItemDto.ItemResponse(ExceptionCode.COMMENT_NOT_FOUND);
        }
    }

    public Object deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ExceptionCode.COMMENT_NOT_FOUND));
        commentRepository.delete(comment);
        return new CommentDto.CommentResponse(ExceptionCode.COMMENT_DELETE_OK);
    }
    public List<CommentDto> getComments(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ExceptionCode.POST_NOT_FOUND));
        return commentRepository.findByPost(post).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CommentDto convertToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setNickname(comment.getNickname());
        commentDto.setContent(comment.getContent());
        commentDto.setPostId(comment.getPost().getPostId());
        return commentDto;
    }

}
