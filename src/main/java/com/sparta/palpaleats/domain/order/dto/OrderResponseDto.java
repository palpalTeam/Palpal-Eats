package com.sparta.palpaleats.domain.order.dto;

import com.sparta.palpaleats.domain.order.entity.Order;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {

    private String orderStatus;
    private String storeName;
    private Long orderId;
    private Long totalPrice;
    private String createdAt;
    private String cart;

    public OrderResponseDto(Order order) {
        this.orderStatus = order.getOrderStatus();
        this.storeName = order.getStore().getName();
        this.orderId = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.createdAt = order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.cart = order.getFirstItemAndOthersCount();
    }
}
