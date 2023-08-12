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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public Object saveComment(Long postId, CommentDto.SaveComment saveComment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ExceptionCode.POST_NOT_FOUND));
        Comment comment = new Comment(saveComment.getNickname(), saveComment.getPassword(), saveComment.getContent(), post);
        commentRepository.save(comment);
        return new CommentDto.CommentResponse(ExceptionCode.COMMENT_SAVE_OK);
    }

    public Object updateComment(Long postId, Long commentId, String password, CommentDto.SaveComment saveComment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ExceptionCode.POST_NOT_FOUND));
        Optional<Comment> findComment = commentRepository.findByCommentId(commentId);

        if (findComment.isPresent() && findComment.get().getPassword().equals(password)) {
            Comment comment = findComment.get();
            comment.updateComment(saveComment);
            commentRepository.save(comment);
            return new CommentDto.CommentResponse(ExceptionCode.COMMENT_UPDATE_OK, getComments(postId), "COMMENT 수정 성공");
            //return getComments(postId);
        } else {
            return new CommentDto.CommentResponse(ExceptionCode.WRONG_PASSWORD, getComments(postId), "잘못된 비밀번호");
        }
    }

    public Object deleteComment(Long postId, Long commentId, String password) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ExceptionCode.POST_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ExceptionCode.COMMENT_NOT_FOUND));
        if (comment.getPassword().equals(password)) {
            commentRepository.delete(comment);
            return new CommentDto.CommentResponse(ExceptionCode.COMMENT_DELETE_OK, getComments(postId), "COMMENT 삭제 성공");
            //return getComments(postId);
        } else {
            return new CommentDto.CommentResponse(ExceptionCode.WRONG_PASSWORD, getComments(postId),"잘못된 비밀번호");
        }
    }
    public List<CommentDto> getComments(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ExceptionCode.POST_NOT_FOUND));
        return commentRepository.findAllByPostPostId(post.getPostId()).stream()
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