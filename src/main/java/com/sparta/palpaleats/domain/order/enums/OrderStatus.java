package com.sparta.palpaleats.domain.order.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    ORDER_START("ORDER_START","주문 시작"),
    ORDER_DELIVERY_ING("ORDER_DELIVERY_ING","주문 배송 중"),
    ORDER_DELIVERY_COMPLETE("ORDER_DELIVERY_COMPLETE","주문 배송 완료"),
    ORDER_CANCELED("ORDER_CANCELED","주문 취소");

    private final String status;
    private final String message;
}
