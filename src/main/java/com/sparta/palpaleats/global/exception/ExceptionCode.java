package com.sparta.palpaleats.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    // BAD_REQUEST
    BAD_REQUEST_NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다"),

    BAD_REQUEST_PASSWORD_ALREADY_USED(HttpStatus.BAD_REQUEST, "최근 3번안에 사용한 비밀번호로는 변경할 수 없습니다"),

    // CONFLICT
    CONFLICT_STORE_NAME(HttpStatus.CONFLICT, "해당 가게 이름은 이미 존재합니다."),

    CONFLICT_USER_EMAIL_NICKNAME_IN_USE(HttpStatus.CONFLICT, "사용자 이메일 또는 닉네임이 이미 사용 중 입니다"),

    CONFLICT_USER_NICKNAME(HttpStatus.CONFLICT, "사용자 닉네임이 이미 사용 중입니다"),

    CONFLICT_CART_ALREADY_ORDERED(HttpStatus.CONFLICT, "해당 장바구니는 이미 주문된 장바구니입니다."),

    CONFLICT_ORDER_ALREADY_CANCELED(HttpStatus.CONFLICT, "해당 주문은 이미 취소된 장바구니입니다."),

    // NOT_FOUND
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당 유저는 존재하지 않습니다."),

    NOT_FOUND_STORE(HttpStatus.NOT_FOUND, "해당 가게는 존재하지 않습니다."),

    NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND, "해당 리뷰는 존재하지 않습니다."),

    NOT_FOUND_MENU(HttpStatus.NOT_FOUND, "해당 메뉴는 존재하지 않습니다."),

    NOT_FOUND_CART(HttpStatus.NOT_FOUND, "해당 장바구니는 존재하지 않습니다."),

    NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, "해당 주문은 존재하지 않습니다."),

    // FORRBIDDEN
    FORBIDDEN_YOUR_NOT_SELLER(HttpStatus.FORBIDDEN, "판매자만 접근할 수 있습니다."),

    FORBIDDEN_YOUR_NOT_STORE_OWNER(HttpStatus.FORBIDDEN, "본인의 가게만 접근할 수 있습니다."),

    FORBIDDEN_YOUR_NOT_STORE_ORDER(HttpStatus.FORBIDDEN, "본인 가게의 주문내역만 접근할 수 있습니다."),
    FORBIDDEN_YOUR_NOT_COME_IN(HttpStatus.FORBIDDEN, "권한이 없습니다"),

    // UNAUTHORIZED 401
    UNAUTHORIZED_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰의 유효기간이 만료되었습니다"),

    UNAUTHORIZED_TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
