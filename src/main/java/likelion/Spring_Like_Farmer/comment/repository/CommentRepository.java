package likelion.Spring_Like_Farmer.comment.repository;

import likelion.Spring_Like_Farmer.comment.domain.Comment;
import likelion.Spring_Like_Farmer.post.domain.Post;
import likelion.Spring_Like_Farmer.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    Optional<Comment> findByCommentId(Long commentId);
}
