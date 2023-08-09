package likelion.Spring_Like_Farmer.user.repository;

import likelion.Spring_Like_Farmer.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    Optional<User> findById(String id);
    Optional<User> findByNickname(String id);

    Optional<User> findByUserId(Long userId);
    Optional<User> findByToken(String token);
    boolean existsById(String id);

    List<User> findByItemContainingOrderByUpdatedAtDesc(String item);
    List<User> findByItemContainingOrderByTierDesc(String item);

}