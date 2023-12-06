package com.sparta.palpaleats.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    // BAD_REQUEST

    // CONFLICT
    CONFLICT_STORE_NAME(HttpStatus.CONFLICT, "해당 가게 이름은 이미 존재합니다."),


    // NOT_FOUND
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당 유저는 존재하지 않습니다."),

    NOT_FOUND_STORE(HttpStatus.NOT_FOUND, "해당 가게는 존재하지 않습니다."),

    NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND, "해당 리뷰는 존재하지 않습니다."),

    NOT_FOUND_MENU(HttpStatus.NOT_FOUND, "해당 메뉴는 존재하지 않습니다."),

    NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, "해당 주문은 존재하지 않습니다."),

    NOT_FOUND_MENU_OPTION(HttpStatus.NOT_FOUND, "해당 메뉴 옵션은 존재하지 않습니다."),

    // FORRBIDDEN
    FORBIDDEN_YOUR_NOT_SELLER(HttpStatus.FORBIDDEN, "판매자만 접근 할 수 있습니다."),

    FORBIDDEN_YOUR_NOT_STORE_OWNER(HttpStatus.FORBIDDEN, "본인의 가게에만 접근할 수 있습니다."),

    FORBIDDEN_YOUR_NOT_COME_IN(HttpStatus.FORBIDDEN, "권한이 없습니다");

    private final HttpStatus httpStatus;
    private final String message;
    }
