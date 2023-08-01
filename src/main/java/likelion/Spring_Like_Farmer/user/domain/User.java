package likelion.Spring_Like_Farmer.user.domain;

import jakarta.persistence.*;
import likelion.Spring_Like_Farmer.config.BaseEntity;
import likelion.Spring_Like_Farmer.user.dto.UserDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@Table(name = "User")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @ColumnDefault("0")
    private int checkInt;
    private String checkId;

    @Column(length = 400)
    private String token;

    private String image;


    // builder
    @Builder
    public User(UserDto.SignupUser signupUser, String pw) {
        this.id = signupUser.getId();
        this.pw = pw;
        this.name = signupUser.getName();
        this.nickname = signupUser.getNickname();
        this.location = signupUser.getLocation();
    }

    /*public void changePassword(String password) {
        this.pw = password;
    }*/

    public void setToken(String token) {
        this.token = token;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
        this.checkInt = 1; // id 중복 확인
    }

    public void setId() {
        id = checkId;
        checkInt = 2; // id 변경 완료
    }
}
