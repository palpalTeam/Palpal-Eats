package com.sparta.palpaleats.domain.user.dto;

import com.sparta.palpaleats.domain.cart.entity.Cart;
import com.sparta.palpaleats.domain.menu.dto.MenuSimpleResponseDto;
import com.sparta.palpaleats.domain.order.entity.Order;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOrderResponseDto {

    private String storeName;

    private Long orderId;

    private Long totalPrice;

    private String paymentMethod;

    private String request;

    private String deliverAddress;

    private String createdAt;

    private List<MenuSimpleResponseDto> menuList;

    public UserOrderResponseDto(Order order) {
        this.storeName = order.getStore().getName();
        this.orderId = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.paymentMethod = order.getPaymentMethod();
        this.request = order.getRequests();
        this.deliverAddress = order.getDeliveryAddress();
        this.createdAt = order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.menuList = order.getCartList().stream().map(Cart::getMenu).map(menu -> {
            MenuSimpleResponseDto dto = new MenuSimpleResponseDto();
            dto.setMenuName(menu.getName());
            dto.setPrice(menu.getPrice());
            return dto;
        }).collect(Collectors.toList());
    }
}
