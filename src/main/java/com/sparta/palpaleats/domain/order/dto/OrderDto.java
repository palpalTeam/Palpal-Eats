package com.sparta.palpaleats.domain.order.dto;

import com.sparta.palpaleats.domain.cart.entity.Cart;
import java.util.ArrayList;
import java.util.List;
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

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetOrderListResponseDto {

        private Long orderId;
        private String orderStatus;
        private String storeName;
        private String createdAt;
        private String deliveryAddress;
        private String paymentMethod;
        private String requestMsg;
        private String cartMsg;
        private Long totalPrice;

    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetOrderResponseDto {

        private Long orderId;
        private String orderStatus;
        private String storeName;
        private String createdAt;
        private String deliveryAddress;
        private String paymentMethod;
        private String requestMsg;
        private Long totalPrice;
        private List<GetCartResponseDto> cartList=new ArrayList<>();

    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetCartResponseDto {

        private String menuName;
        private Integer quantity;
        private Long totalPrice;
    }

}
