package likelion.Spring_Like_Farmer.user.controller;

import likelion.Spring_Like_Farmer.item.dto.ItemDto;
import likelion.Spring_Like_Farmer.security.CurrentUser;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import likelion.Spring_Like_Farmer.user.dto.UserDto;
import likelion.Spring_Like_Farmer.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/auth/signup")
    public Object signup(@RequestBody UserDto.SignupUser signupUser) {
        return new ResponseEntity<>(userService.signup(signupUser), HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@RequestBody UserDto.LoginUser loginUser) {
        return new ResponseEntity<>(userService.login(loginUser), HttpStatus.OK);
    }

    // 프로필 조회
    @GetMapping("/user/profile/{userId}")
    public ResponseEntity<Object> getUsersInfo(@CurrentUser UserPrincipal userPrincipal,
                                        @PathVariable Long userId) {
        return new ResponseEntity<>(userService.findUser(userPrincipal, userId), HttpStatus.OK);
    }

    // 티어 기준 유저 리스트
    @PostMapping("/user/tier")
    public ResponseEntity<Object> getUsersTier(@CurrentUser UserPrincipal userPrincipal,
                                              @RequestBody UserDto.FindUsers findUser) {
        return new ResponseEntity<>(userService.findUsersTier(userPrincipal, findUser.getKeyword()), HttpStatus.OK);
    }

    // 농작물 기준 유저 리스트
    @PostMapping("/user/post")
    public ResponseEntity<Object> getUserPost(@CurrentUser UserPrincipal userPrincipal,
                                              @RequestBody UserDto.FindUsers findUser) {
        return new ResponseEntity<>(userService.findUsersPost(userPrincipal, findUser.getKeyword()), HttpStatus.OK);
    }

    // 유저 홈 정보
    @GetMapping("/user/home")
    public ResponseEntity<Object> getUsersInfo(@CurrentUser UserPrincipal userPrincipal) {
        return new ResponseEntity<>(userService.findUserInfo(userPrincipal), HttpStatus.OK);
    }

    // 프로필 수정
    @PatchMapping("/user/update")
    public ResponseEntity<Object> getUserInfo(@CurrentUser UserPrincipal userPrincipal,
                                              @RequestBody UserDto.UpdateUser updateUser) {
        System.out.println("UserController.getUserInfo-------------------------1");
        System.out.println("updateUser = " + updateUser.getNickname());
        return new ResponseEntity<>(userService.updateUser(userPrincipal, updateUser, null), HttpStatus.OK);
    }

    // 프로필 수정 : 파일 추가
    @PatchMapping("/user/file")
    public ResponseEntity<Object> getUserInfoFile(@CurrentUser UserPrincipal userPrincipal,
                                              @RequestPart(value = "file", required = false) MultipartFile file) {
        System.out.println("UserController.getUserInfoFile-----------------");
        System.out.println("file = " + file);
        return new ResponseEntity<>(userService.updateUserFile(userPrincipal, file), HttpStatus.OK);
    }

    // 프로필 수정 : 파일 추가
    @PatchMapping("/user")
    public ResponseEntity<Object> getUserInfo(@CurrentUser UserPrincipal userPrincipal,
                                              @RequestPart(value = "user")UserDto.UpdateUser updateUser,
                                              @RequestPart(value = "file", required = false) MultipartFile file) {
        System.out.println("UserController.getUserInfo-------------------------2");
        System.out.println("updateUser = " + updateUser.getNickname());
        System.out.println("file = " + file);
        return new ResponseEntity<>(userService.updateUser(userPrincipal, updateUser, file), HttpStatus.OK);
    }

    // 글 올리기 -> tier 3

    /*@PostMapping("/user/logout") // 로그아웃
    public ResponseEntity<Object> logout(@CurrentUser UserPrincipal userPrincipal) {
        return new ResponseEntity<>(userService.logout(userPrincipal), HttpStatus.OK);
    }

    @DeleteMapping("/user/resign") // 회원 탈퇴
    public ResponseEntity<Object> resign(@CurrentUser UserPrincipal userPrincipal) {
        return new ResponseEntity<>(userService.resign(userPrincipal), HttpStatus.OK);
    }*/
}
