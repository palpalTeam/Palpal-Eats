package com.sparta.palpaleats.global.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonResponseCode {

    // Store_Creat
    STORE_CREATE(HttpStatus.CREATED, "가게 등록에 성공했습니다."),
    // Store_UPDATE
    STORE_UPDATE(HttpStatus.OK, "가게 변경에 성공했습니다."),
    //Menu_ADD
    MENU_CREATE(HttpStatus.CREATED, "메뉴 등록에 성공했습니다.");


    private final HttpStatus httpStatus;
    private final String message;
}