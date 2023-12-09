package com.sparta.palpaleats.domain.user.service;

import com.sparta.palpaleats.domain.order.dto.OrderResponseDto;
import com.sparta.palpaleats.domain.order.entity.Order;
import com.sparta.palpaleats.domain.order.repository.OrderRepository;
import com.sparta.palpaleats.domain.password.entity.Password;
import com.sparta.palpaleats.domain.password.repository.PasswordRepository;
import com.sparta.palpaleats.domain.user.dto.UserAddressUpdateRequestDto;
import com.sparta.palpaleats.domain.user.dto.UserNicknameUpdateRequestDto;
import com.sparta.palpaleats.domain.user.dto.UserOrderResponseDto;
import com.sparta.palpaleats.domain.user.dto.UserPasswordUpdateRequestDto;
import com.sparta.palpaleats.domain.user.dto.UserResponseDto;
import com.sparta.palpaleats.domain.user.dto.UserSaveRequestDto;
import com.sparta.palpaleats.domain.user.entity.User;
import com.sparta.palpaleats.domain.user.entity.UserRoleEnum;
import com.sparta.palpaleats.domain.user.repository.UserRepository;
import com.sparta.palpaleats.global.exception.CustomException;
import com.sparta.palpaleats.global.exception.ExceptionCode;
import com.sparta.palpaleats.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;
    private final JwtUtil jwtUtil;

    public void signup(UserSaveRequestDto requestDto) {

        String email = requestDto.getEmail();
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        String currentAddress = requestDto.getCurrentAddress();
        String nickname = requestDto.getNickname();
        Boolean isSeller = requestDto.getIsSeller();
        User user;

        if (userRepository.existsByEmailOrNickname(email, nickname)) {
            throw new CustomException(ExceptionCode.CONFLICT_USER_EMAIL_NICKNAME_IN_USE);
        }

        if (isSeller) {
            UserRoleEnum role = UserRoleEnum.SELLER;
            user = new User(email, encodedPassword, currentAddress, nickname, role);
            userRepository.save(user);
        } else {
            UserRoleEnum role = UserRoleEnum.BUYER;
            user = new User(email, encodedPassword, currentAddress, nickname, role);
            userRepository.save(user);
        }

        Password password = new Password(user);

        passwordRepository.save(password);
    }

    public void logout(HttpServletRequest request) {

        jwtUtil.deleteRefreshToken(request);
    }

    // 월요일에 튜터님께 여쭤보겠습니다.
//    public void logout2(String username) {
//
//        jwtUtil.deleteRefreshToken2(username);
//    }

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

        if(userRepository.existsByNickname(nickname)) {
            throw new CustomException(ExceptionCode.CONFLICT_USER_NICKNAME);
        }

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

        List<Password> passwordList = passwordRepository.findAllByUserIdOrderByCreatedAt(user.getId());

        for (Password password : passwordList) {
            if (passwordEncoder.matches(requestDto.getNewPassword(), password.getPassword())) {
                throw new CustomException(ExceptionCode.BAD_REQUEST_PASSWORD_ALREADY_USED);
            }
        }

        if(passwordList.size() >= 3) {
            passwordRepository.delete(passwordList.get(0));
        }
        user.setPassword(newPassword);

        Password password = new Password(user);

        passwordRepository.save(password);
    }

    public UserResponseDto getMyInfo(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );

        return new UserResponseDto(user);
    }


    public UserOrderResponseDto getUserOrder(Long id, Long orderId) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );

        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_ORDER)
        );

        if (!order.getUser().getId().equals(user.getId())) {
            throw new CustomException(ExceptionCode.FORBIDDEN_YOUR_NOT_COME_IN);
        }

        return new UserOrderResponseDto(order);
    }

    public List<OrderResponseDto> getUserOrders(Long id) {
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );

        List<Order> orderList = orderRepository.findAllByUserId(user.getId());

        for (Order order : orderList) {
            OrderResponseDto orderResponseDto = new OrderResponseDto(order);
            orderResponseDtoList.add(orderResponseDto);
        }

        return orderResponseDtoList;
    }
}
