package likelion.Spring_Like_Farmer.comment.repository;

import likelion.Spring_Like_Farmer.comment.domain.Comment;
import likelion.Spring_Like_Farmer.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
