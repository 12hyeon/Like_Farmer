package likelion.Spring_Like_Farmer.user.service;

import likelion.Spring_Like_Farmer.file.FileService;
import likelion.Spring_Like_Farmer.item.domain.Item;
import likelion.Spring_Like_Farmer.item.repository.ItemRepository;
import likelion.Spring_Like_Farmer.record.domain.Record;
import likelion.Spring_Like_Farmer.record.repository.RecordRepository;
import likelion.Spring_Like_Farmer.security.TokenProvider;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import likelion.Spring_Like_Farmer.user.domain.User;
import likelion.Spring_Like_Farmer.user.dto.TokenDto;
import likelion.Spring_Like_Farmer.user.dto.UserDto;
import likelion.Spring_Like_Farmer.user.repository.UserRepository;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final RecordRepository recordRepository;
    private final ItemRepository itemRepository;
    private final TokenProvider tokenProvider;
    private final FileService fileService;
    private final AuthenticationManager authenticationManager;

    public TokenDto createToken(Authentication authentication, Long userId) {

        String accessToken = tokenProvider.createToken(authentication, Boolean.FALSE); // access
        String refreshToken = tokenProvider.createToken(authentication, Boolean.TRUE); // refresh

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Object signup(UserDto.SignupUser signupUser) {

        Optional<User> findUser = userRepository.findById(signupUser.getId());
        if (findUser.isPresent()) {
            return new UserDto.DuplicateUserResponse(ExceptionCode.SIGNUP_DUPLICATED_ID); // ID 중복
        }

        findUser = userRepository.findByNickname(signupUser.getNickname());
        if (findUser.isPresent()) {
            return new UserDto.DuplicateUserResponse(ExceptionCode.SIGNUP_DUPLICATED_NICKNAME); // NICKNAME 중복
        }

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = new User(signupUser, encoder.encode(signupUser.getPw()));
        userRepository.save(user);

        return new UserDto.UserResponse(ExceptionCode.SIGNUP_CREATED_OK);
    }

    public Object login(UserDto.LoginUser loginUser) {

        Optional<User> findUser = userRepository.findById(loginUser.getId());

        if (findUser.isEmpty()) {
            return new UserDto.DuplicateUserResponse(ExceptionCode.LOGIN_NOT_FOUND_ID);
        } else if (! PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(loginUser.getPw(), findUser.get().getPw())) {
            return new UserDto.DuplicateUserResponse(ExceptionCode.LOGIN_NOT_FOUND_PW);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        findUser.get().getId(),
                        loginUser.getPw()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenDto tokenDto = createToken(authentication, findUser.get().getUserId());
        findUser.get().setToken(tokenDto.getRefreshToken());
        userRepository.save(findUser.get());

        return new UserDto.LoginResponse(ExceptionCode.LOGIN_OK, findUser.get(), tokenDto);
    }

    public Object updateUser(UserPrincipal userPrincipal, UserDto.UpdateUser updateUser, MultipartFile file) {

        Optional<User> findUser = userRepository.findByUserId(userPrincipal.getUserId());
        if (findUser.isEmpty()) {
            return new UserDto.UserResponse(ExceptionCode.USER_NOT_FOUND);
        }
        User user = findUser.get();

        Optional<User> byNickname = userRepository.findByNickname(updateUser.getNickname());
        if (byNickname.isPresent() && ! byNickname.get().getUserId().equals(userPrincipal.getUserId())) {
            return new UserDto.UserResponse(ExceptionCode.SIGNUP_DUPLICATED_NICKNAME);
        }

        user.updateUser(updateUser);

        if (file != null) {
            String image = fileService.saveFile(user.getUserId(), file, "profile");
            user.setImage(image);
        } else {
            user.setImage(null);
        }

        userRepository.save(user);

        return new UserDto.UserResponse(ExceptionCode.USER_UPDATE_OK);
    }

    // 검색
    public Object findUsersTier(UserPrincipal userPrincipal, String keyword) {
        List<User> findUser = userRepository.findByItemContainingOrderByTierDesc(keyword);
        return new UserDto.UsersInfoResponse(ExceptionCode.USER_SEARCH_OK, findUser);
    }

    public Object findUsersPost(UserPrincipal userPrincipal, String keyword) {
        List<User> findUser = userRepository.findByItemContainingOrderByUpdatedAtDesc(keyword);
        return new UserDto.UsersInfoResponse(ExceptionCode.USER_SEARCH_OK, findUser);
    }


    // home
    public Object findUserInfo(UserPrincipal userPrincipal) {
        User user = userRepository.findByUserId(userPrincipal.getUserId()).get();
        return new UserDto.UsersInfoResponse(ExceptionCode.USER_SEARCH_OK, user);
    }


    // 전체 조회
    public Object findUser(UserPrincipal userPrincipal, Long userId) {

        Optional<User> findUser = userRepository.findByUserId(userId);
        if (findUser.isEmpty()) {
            return new UserDto.UserResponse(ExceptionCode.USER_NOT_FOUND);
        }
        User user = findUser.get();

        List<Item> items = itemRepository.findAllByUserUserId(user.getUserId());
        List<Record> records = recordRepository.findAllByUserUserId(user.getUserId());

        return new UserDto.UserInfoResponse(ExceptionCode.USER_GET_OK, user, items, records);
    }
}

