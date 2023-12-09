package com.sparta.palpaleats.domain.order.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentMethod {

    CARD("CARD"),
    CASH("CASH"),
    MOBILE_PHONE_PAY("MOBILE_PHONE_PAY"),

    NAVER_PAY("NAVER_PAY"),
    KAKAO_PAY("KAKAO_PAY"),
    PALPAL_PAY("PALPAL_PAY");

    private final String method;

}
