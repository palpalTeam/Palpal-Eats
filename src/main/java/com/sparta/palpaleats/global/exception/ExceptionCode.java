package com.sparta.palpaleats.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    // BAD_REQUEST
    BAD_REQUEST_NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다"),

    // CONFLICT
    CONFLICT_STORE_NAME(HttpStatus.CONFLICT, "해당 가게 이름은 이미 존재합니다."),

    CONFLICT_USER_EMAIL_NICKNAME_IN_USE(HttpStatus.CONFLICT, "사용자 이메일 또는 닉네임이 이미 사용 중 합니다"),


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

    FORBIDDEN_YOUR_NOT_COME_IN(HttpStatus.FORBIDDEN, "권한이 없습니다"),

    // UNAUTHORIZED 401
    UNAUTHORIZED_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰의 유효기간이 만료되었습니다"),

    UNAUTHORIZED_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.");

    private final HttpStatus httpStatus;
    private final String message;
    }
