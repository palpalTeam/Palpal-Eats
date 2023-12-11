package com.sparta.palpaleats.domain.user.controller;

import com.sparta.palpaleats.domain.order.dto.OrderResponseDto;
import com.sparta.palpaleats.domain.user.dto.UserAddressUpdateRequestDto;
import com.sparta.palpaleats.domain.user.dto.UserNicknameUpdateRequestDto;
import com.sparta.palpaleats.domain.user.dto.UserOrderResponseDto;
import com.sparta.palpaleats.domain.user.dto.UserPasswordUpdateRequestDto;
import com.sparta.palpaleats.domain.user.dto.UserResponseDto;
import com.sparta.palpaleats.domain.user.dto.UserSaveRequestDto;
import com.sparta.palpaleats.domain.user.service.UserService;
import com.sparta.palpaleats.global.dto.CommonResponseDto;
import com.sparta.palpaleats.global.exception.CustomException;
import com.sparta.palpaleats.global.exception.ExceptionCode;
import com.sparta.palpaleats.global.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<CommonResponseDto> signup(
            @Valid @RequestBody UserSaveRequestDto requestDto) {

        userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponseDto(HttpStatus.CREATED.value(), "가입이 완료되었습니다."));
    }

    @PatchMapping("/users/logout")
    public ResponseEntity<CommonResponseDto> logout(HttpServletRequest request) {

        userService.logout(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponseDto(HttpStatus.OK.value(), "로그아웃이 완료되었습니다"));
    }

    // 월요일에 튜터님께 여쭤보겠습니다.
//    @PatchMapping("/users/logout2")
//    public ResponseEntity<CommonResponseDto> logout2(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//
//        userService.logout2(userDetails.getUsername());
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new CommonResponseDto(HttpStatus.OK.value(), "로그아웃이 완료되었습니다"));
//    }

    @GetMapping("/myinfo")
    public ResponseEntity<UserResponseDto> getMyInfo(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        UserResponseDto responseDto = userService.getMyInfo(userDetails.getUser().getId());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/myinfo/address")
    public ResponseEntity<CommonResponseDto> updateUserAddress(@AuthenticationPrincipal
    UserDetailsImpl userDetails, @Valid @RequestBody UserAddressUpdateRequestDto requestDto) {

        userService.updateUserAddress(userDetails.getUser().getId(), requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponseDto(HttpStatus.OK.value(), "주소가 수정되었습니다"));
    }

    @PatchMapping("/myinfo/nickname")
    public ResponseEntity<CommonResponseDto> updateUserNickname(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody UserNicknameUpdateRequestDto requestDto) {

        userService.updateUserNickname(userDetails.getUser().getId(), requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponseDto(HttpStatus.OK.value(), "닉네임이 수정되었습니다"));
    }

    @PatchMapping("/myinfo/password")
    public ResponseEntity<CommonResponseDto> updateUserPassword(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody UserPasswordUpdateRequestDto requestDto) {

        userService.updateUserPassword(userDetails.getUser().getId(), requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponseDto(HttpStatus.OK.value(), "비밀번호가 수정되었습니다"));
    }

    @GetMapping("/myinfo/orders/{orderId}")
    public ResponseEntity<UserOrderResponseDto> getUserOrder(
            @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long orderId) {

        UserOrderResponseDto responseDto = userService.getUserOrder(userDetails.getUser().getId(),
                orderId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/myinfo/orders")
    public ResponseEntity<List<OrderResponseDto>> getUserOrders(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        List<OrderResponseDto> orderResponseDto = userService.getUserOrders(
                userDetails.getUser().getId());

        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }
}
