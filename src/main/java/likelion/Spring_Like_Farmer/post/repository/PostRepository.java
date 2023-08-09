package likelion.Spring_Like_Farmer.post.repository;

import likelion.Spring_Like_Farmer.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

