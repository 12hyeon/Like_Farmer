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
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getUserInfo(@CurrentUser UserPrincipal userPrincipal,
                                        @PathVariable Long userId) {
        return new ResponseEntity<>(userService.findUser(userPrincipal, userId), HttpStatus.OK);
    }

    // 프로필 수정
    @PatchMapping("/user")
    public ResponseEntity<Object> getUserInfo(@CurrentUser UserPrincipal userPrincipal,
                                              @RequestBody UserDto.UpdateUser updateUser) {
        return new ResponseEntity<>(userService.updateUser(userPrincipal, updateUser), HttpStatus.OK);
    }

    // 프로필 사진 올리기 -> tier 2

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
