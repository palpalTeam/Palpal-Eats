package com.sparta.palpaleats.domain.user.controller;

import com.sparta.palpaleats.domain.user.dto.UserSaveRequestDto;
import com.sparta.palpaleats.domain.user.service.UserService;
import com.sparta.palpaleats.global.dto.CommonResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
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
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDto(HttpStatus.CREATED.value(), "가입이 완료되었습니다."));
    }

    @PatchMapping("/users/logout")
    public ResponseEntity<CommonResponseDto> logout(HttpServletRequest request) {

        userService.logout(request);
        return ResponseEntity.status(HttpStatus.OK.value())
                .body(new CommonResponseDto(HttpStatus.OK.value(), "로그아웃이 완료되었습니다"));
    }
}
