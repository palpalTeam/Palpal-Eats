package com.sparta.palpaleats.domain.user.controller;

import com.sparta.palpaleats.domain.user.dto.UserSaveRequestDto;
import com.sparta.palpaleats.domain.user.service.UserService;
import com.sparta.palpaleats.global.dto.CommonResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody UserSaveRequestDto requestDto) {

        userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new CommonResponseDto(HttpStatus.CREATED.value(), "가입이 완료되었습니다."));
    }
}
