package likelion.Spring_Like_Farmer.user.domain;

import jakarta.persistence.*;
import likelion.Spring_Like_Farmer.config.BaseEntity;
import likelion.Spring_Like_Farmer.user.dto.RecordDto;
import likelion.Spring_Like_Farmer.user.dto.UserDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

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

    private String image; // 처리 로직 필요!

    @Column(length = 400)
    private String token;

    private int tier;
    private Quest.QuestInfo description;
    private String item;

    private String phone;
    private String field;
    private String spec;
    private String license;

    // builder
    @Builder
    public User(UserDto.SignupUser signupUser, String pw) {
        this.id = signupUser.getId();
        this.pw = pw;
        this.name = signupUser.getName();
        this.nickname = signupUser.getNickname();
        this.location = signupUser.getLocation();
        this.tier = 1;
        this.item = "풀스택";
        this.description = Quest.QuestInfo.ONE;
    }

    public void updateUser(UserDto.UpdateUser updateUser) {
        this.nickname = updateUser.getNickname();
        this.location = updateUser.getLocation();

        this.phone = updateUser.getPhone();
        this.field = updateUser.getField();
        this.spec = updateUser.getSpec();
        this.license = updateUser.getLicense();
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setItem(String newItem) {
        this.item = newItem + " " + item;
    }

    public void setImage(String image) {
        this.image = image;
        if (tier == 1) {
            tier = 2;
            description = Quest.QuestInfo.TWO;
        }
    }

}
