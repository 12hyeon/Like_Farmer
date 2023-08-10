package likelion.Spring_Like_Farmer.post.repository;

import likelion.Spring_Like_Farmer.item.domain.Item;
import likelion.Spring_Like_Farmer.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedDateDesc();
    Optional<Post> findByPostId(Long postId);
}

