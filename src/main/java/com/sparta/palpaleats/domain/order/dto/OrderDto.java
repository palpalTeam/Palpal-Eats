package com.sparta.palpaleats.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateOrderRequestDto {
        private String deliveryAddress;
        private String paymentMethod;
        private String requestMsg;
    }
}
