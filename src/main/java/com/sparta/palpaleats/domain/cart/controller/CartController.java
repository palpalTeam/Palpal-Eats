package com.sparta.palpaleats.domain.cart.controller;

import com.sparta.palpaleats.domain.cart.dto.CartDto;
import com.sparta.palpaleats.domain.cart.service.CartService;
import com.sparta.palpaleats.global.dto.CommonResponseDto;
import com.sparta.palpaleats.global.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("")
    public ResponseEntity<CommonResponseDto> createCart(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CartDto.CreateCartRequestDto requestDto
        ) throws Exception {

        return ResponseEntity.ok()
                .body(cartService.createCart(userDetails.getUser(),requestDto));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<CommonResponseDto> deleteCart(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long cartId
    ) throws Exception {

        return ResponseEntity.ok()
                .body(cartService.deleteCart(userDetails.getUser(),cartId));
    }

    @PatchMapping("/{cartId}")
    public ResponseEntity<CommonResponseDto> updateCart(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long cartId,
            @RequestBody CartDto.UpdateCartRequestDto requestDto
    ) throws Exception {

        return ResponseEntity.ok()
                .body(cartService.updateCart(userDetails.getUser(),cartId,requestDto));
    }

    @GetMapping("")
    public ResponseEntity<List<CartDto.GetCartResponseDto>> getCart(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) throws Exception {

        return ResponseEntity.ok()
                .body(cartService.getCart(userDetails.getUser()));
    }
}
