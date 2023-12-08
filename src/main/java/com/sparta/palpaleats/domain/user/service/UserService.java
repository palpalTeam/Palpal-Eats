package com.sparta.palpaleats.domain.user.service;

import com.sparta.palpaleats.domain.user.dto.UserAddressUpdateRequestDto;
import com.sparta.palpaleats.domain.user.dto.UserNicknameUpdateRequestDto;
import com.sparta.palpaleats.domain.user.dto.UserPasswordUpdateRequestDto;
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
import org.springframework.transaction.annotation.Transactional;

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

        if (userRepository.existsByEmailOrNickname(email, nickname)) {
            throw new CustomException(ExceptionCode.CONFLICT_USER_EMAIL_NICKNAME_IN_USE);
        }

        if (isSeller) {
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

    @Transactional
    public void updateUserAddress(Long userId, UserAddressUpdateRequestDto requestDto) {

        String address = requestDto.getAddress();

        User user = userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );

        user.setCurrentAddress(address);
    }

    @Transactional
    public void updateUserNickname(Long id, UserNicknameUpdateRequestDto requestDto) {

        String nickname = requestDto.getNickname();

        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );

        user.setNickname(nickname);
    }

    @Transactional
    public void updateUserPassword(Long id, UserPasswordUpdateRequestDto requestDto) {

        String oldPassword = requestDto.getOldPassword();
        String newPassword = passwordEncoder.encode(requestDto.getNewPassword());

        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new CustomException(ExceptionCode.BAD_REQUEST_NOT_MATCH_PASSWORD);
        }

        user.setPassword(newPassword);
    }
}
