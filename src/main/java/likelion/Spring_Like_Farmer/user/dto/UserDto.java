package likelion.Spring_Like_Farmer.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import likelion.Spring_Like_Farmer.config.ResponseType;
import likelion.Spring_Like_Farmer.user.domain.User;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.Data;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class UserDto {

    @Getter
    public static class SignupUser {
        String id;
        String pw;
        String name;
        String nickname;
        String location;
    }

    @Getter
    public static class UpdateUser {
        String nickname;
        String location;
        String description;
        List<User> items;
    }

    @Getter
    public static class LoginUser {
        String id;
        String pw;
    }

    @Getter
    public static class LoginResponse extends ResponseType {
        Long userId;
        String nickname;
        TokenDto tokenDto;

        public LoginResponse(ExceptionCode exceptionCode, User user, TokenDto tokenDto) {
            super(exceptionCode);
            this.userId = user.getUserId();
            this.nickname = user.getNickname();
            this.tokenDto = tokenDto;
        }
    }

    @Getter
    public static class UserInfoResponse extends ResponseType {
        Long userId;
        String name;
        String nickname;
        String location;

        public UserInfoResponse(ExceptionCode exceptionCode, User user) {
            super(exceptionCode);
            this.userId = user.getUserId();
            this.nickname = user.getNickname();
            this.name = user.getName();
            this.location = user.getLocation();
        }
    }

    @Getter
    public static class TokenResponse extends ResponseType {
        TokenDto tokenDto;

        public TokenResponse(ExceptionCode exceptionCode, TokenDto tokenDto) {
            super(exceptionCode);
            this.tokenDto = tokenDto;
        }
    }

    @Getter
    public static class UserResponse extends ResponseType {

        @JsonInclude(NON_NULL)
        Long userId;

        public UserResponse(ExceptionCode exceptionCode) {
            super(exceptionCode);
        }

        public UserResponse(ExceptionCode exceptionCode, Long userId) {
            super(exceptionCode);
            this.userId = userId;
        }
    }

    @Getter
    public static class DuplicateUserResponse extends ResponseType {

        public DuplicateUserResponse(ExceptionCode exceptionCode) {
            super(exceptionCode);
        }
    }

    @Getter
    public static class UserListResponse extends ResponseType{

        List<UserList> userList;
        Long total;

        public UserListResponse(ExceptionCode exceptionCode, Long total, List<UserList> userList) {
            super(exceptionCode);
            this.total = total;
            this.userList = userList;
        }
    }

    @Data
    public static class UserList {

        Long userId;
        String id;
        String userName;
        String file;

        public UserList(User user) {
            this.userId = user.getUserId();
            this.id = user.getId();
            this.userName = user.getName();
        }

        public UserList setFile(String file) {
            this.file = file;
            return this;
        }
    }

    @Data
    public static class UserInfoList {

        Long userId;
        String id;
        String userName;
        String file;

        public UserInfoList(User user) {
            this.userId = user.getUserId();
            this.id = user.getId();
            this.userName = user.getName();
        }

        public UserInfoList setFile(String file) {
            this.file = file;
            return this;
        }
    }

    @Data
    public static class UserInfo {

        Long userId;
        String id;
        String userName;

        ImageDto.ImageUrlResponse image;

        public UserInfo(User user, ImageDto.ImageUrlResponse imageUrlResponse) {
            this.userId = user.getUserId();
            this.id = user.getId();
            this.userName = user.getName();
            this.image = imageUrlResponse;

        }
    }
}
