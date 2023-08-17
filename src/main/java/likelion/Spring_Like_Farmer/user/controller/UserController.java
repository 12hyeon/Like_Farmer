package likelion.Spring_Like_Farmer.user.controller;

import likelion.Spring_Like_Farmer.post.service.PostService;
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
    private final PostService postService;

    @PostMapping("/auth/signup")
    public Object signup(@RequestBody UserDto.SignupUser signupUser) {
        return new ResponseEntity<>(userService.signup(signupUser), HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@RequestBody UserDto.LoginUser loginUser) {
        return new ResponseEntity<>(userService.login(loginUser), HttpStatus.OK);
    }

    // 프로필 조회
    @GetMapping("/auth/profile/{userId}")
    public ResponseEntity<Object> getUsersInfo(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.findUser(userId), HttpStatus.OK);
    }

    // 티어 기준 유저 리스트
    @PostMapping("/auth/tier")
    public ResponseEntity<Object> getUsersTier(@RequestBody UserDto.FindUsers findUser) {
        return new ResponseEntity<>(userService.findUsersTier(findUser.getKeyword()), HttpStatus.OK);
    }

    // 농작물 기준 유저 리스트
    @PostMapping("/auth/post")
    public ResponseEntity<Object> getUserPost(@RequestBody UserDto.FindUsers findUser) {
        return new ResponseEntity<>(userService.findUsersPost(findUser.getKeyword()), HttpStatus.OK);
    }

    // 유저 홈 정보
    @GetMapping("/auth/home/{userId}")
    public ResponseEntity<Object> getUsersInfo2(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.findUserInfo(userId), HttpStatus.OK);
    }

    // 프로필 수정
    @PatchMapping("/user/update")
    public ResponseEntity<Object> fixUserInfo(@CurrentUser UserPrincipal userPrincipal,
                                              @RequestBody UserDto.UpdateUser updateUser) {
        return new ResponseEntity<>(userService.updateUser(userPrincipal, updateUser, null), HttpStatus.OK);
    }

    // 프로필 수정 : 파일 추가
    @PatchMapping("/user/file")
    public ResponseEntity<Object> fixUserInfoFile(@CurrentUser UserPrincipal userPrincipal,
                                                  @RequestPart(value = "file", required = false) MultipartFile file) {
        return new ResponseEntity<>(userService.updateUserFile(userPrincipal, file), HttpStatus.OK);
    }

    // 임시
    @PatchMapping("/user")
    public ResponseEntity<Object> getUserInfo(@CurrentUser UserPrincipal userPrincipal,
                                              @RequestPart(value = "user")UserDto.UpdateUser updateUser,
                                              @RequestPart(value = "file", required = false) MultipartFile file) {
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

    // 게시물 전체 불러오기
    @GetMapping("/auth/post")
    public ResponseEntity<Object> getAllPosts() {
        return new ResponseEntity<>(postService.findAllPosts(), HttpStatus.OK);
    }
}
