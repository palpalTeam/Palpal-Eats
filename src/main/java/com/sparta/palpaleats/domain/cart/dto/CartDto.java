package com.sparta.palpaleats.domain.cart.dto;

import com.sparta.palpaleats.domain.menu.entity.Menu;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CartDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateCartRequestDto {
        private Long menuId;
        private Integer quantity;  //수량
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateCartRequestDto {
        private Long menuId;
        private Integer quantity;  //수량
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetCartResponseDto {
        private String storeName;
        private String menuName;
        private Long totalPrice;
        private Integer quantity;  //수량
    }

}