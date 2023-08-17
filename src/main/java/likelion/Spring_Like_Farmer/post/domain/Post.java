package likelion.Spring_Like_Farmer.post.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import likelion.Spring_Like_Farmer.comment.domain.Comment;
import likelion.Spring_Like_Farmer.config.BaseEntity;
import likelion.Spring_Like_Farmer.post.dto.PostDto;
import likelion.Spring_Like_Farmer.user.domain.Quest;
import likelion.Spring_Like_Farmer.user.domain.User;
import lombok.*;

import java.util.List;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_image")
    private String userImage;

    @Column(name = "user_nickname")
    private String userNickname;

    private String location;

    @Column
    private String image;

    private String description;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Builder
    public Post(User user, PostDto.SavePost savePost) {
        this.user = user;
        this.userImage = user.getImage();
        this.userNickname = user.getNickname();
        this.location = savePost.getLocation();
        this.userImage = user.getImage();
        // this.image = savePostContent.getImage();
        this.description = savePost.getDescription();
    }
    public void updatePost(PostDto.SavePost savePost) {
        this.location = savePost.getLocation();
        // this.image = savePost.getImage();
        this.description = savePost.getDescription();
    }

    public void setImage(String image) {
        this.image = image;
    }
}
