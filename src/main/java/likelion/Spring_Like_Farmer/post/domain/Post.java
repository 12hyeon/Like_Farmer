package likelion.Spring_Like_Farmer.post.domain;

import jakarta.persistence.*;
import likelion.Spring_Like_Farmer.config.BaseEntity;
import likelion.Spring_Like_Farmer.user.domain.User;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "Post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_image")
    private String userImage;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_location")
    private String userLocation;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String comment;

    @Builder
    public Post(User user, String userImage, String userNickname,
                String userLocation, String image, String description, String comment) {
        this.user = user;
        this.userImage = userImage;
        this.userNickname = userNickname;
    }
}
