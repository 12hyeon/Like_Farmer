package likelion.Spring_Like_Farmer.comment.domain;

import jakarta.persistence.*;
import likelion.Spring_Like_Farmer.config.BaseEntity;
import likelion.Spring_Like_Farmer.post.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String nickname, String password, String content, Post post) {
        this.nickname = nickname;
        this.password = password;
        this.content = content;
        this.post = post;
    }
}

