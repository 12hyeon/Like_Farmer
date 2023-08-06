package likelion.Spring_Like_Farmer.comment.repository;

import likelion.Spring_Like_Farmer.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
