package com.sparta.palpaleats.domain.store.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonResponseCode {

    // Store_Creat
    STORE_CREATE(HttpStatus.CREATED, "가게 등록에 성공했습니다."),
    // Store_Set-image
    STORE_SET_IMAGE(HttpStatus.OK, "가게 이미지 등록에 성공했습니다");


    private final HttpStatus httpStatus;
    private final String message;
}
