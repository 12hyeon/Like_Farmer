package likelion.Spring_Like_Farmer.security;

import likelion.Spring_Like_Farmer.user.domain.User;
import likelion.Spring_Like_Farmer.user.repository.UserRepository;
import likelion.Spring_Like_Farmer.validation.CustomException;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService { // 로그인된 사용자 처리

    @Autowired
    private UserRepository userRepository;
    private static final Integer STATUS = 1;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findById(email)
                .orElseThrow(() -> {
                    throw new CustomException(ExceptionCode.USER_NOT_FOUND);
                });

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findByUserId(id)
                .orElseThrow(() -> {
                    throw new CustomException(ExceptionCode.USER_NOT_FOUND);
                });

        return UserPrincipal.create(user);
    }
}