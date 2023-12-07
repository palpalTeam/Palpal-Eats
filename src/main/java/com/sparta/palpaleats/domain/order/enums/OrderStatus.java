package com.sparta.palpaleats.domain.order.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    ORDER_START("주문 시작"),
    ORDER_DELIVERY_ING("주문 배송 중"),
    ORDER_DELIVERY_COMPLETE("주문 배송 완료");

    private final String status;
}
