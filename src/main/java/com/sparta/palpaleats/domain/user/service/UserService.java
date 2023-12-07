package com.sparta.palpaleats.domain.user.service;

import com.sparta.palpaleats.domain.user.dto.UserSaveRequestDto;
import com.sparta.palpaleats.domain.user.entity.User;
import com.sparta.palpaleats.domain.user.entity.UserRoleEnum;
import com.sparta.palpaleats.domain.user.repository.UserRepository;
import com.sparta.palpaleats.global.exception.CustomException;
import com.sparta.palpaleats.global.exception.ExceptionCode;
import com.sparta.palpaleats.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void signup(UserSaveRequestDto requestDto) {

        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String currentAddress = requestDto.getCurrentAddress();
        String nickname = requestDto.getNickname();
        Boolean isSeller = requestDto.getIsSeller();

        if(userRepository.existsByEmailOrNickname(email, nickname)) {
            throw new CustomException(ExceptionCode.CONFLICT_USER_EMAIL_NICKNAME_IN_USE);
        }

        if(isSeller) {
            UserRoleEnum role = UserRoleEnum.SELLER;
            User user = new User(email, password, currentAddress, nickname, role);
            userRepository.save(user);
        } else {
            UserRoleEnum role = UserRoleEnum.BUYER;
            User user = new User(email, password, currentAddress, nickname, role);
            userRepository.save(user);
        }

    }

    public void logout(HttpServletRequest request) {

        jwtUtil.deleteRefreshToken(request);
    }
}
