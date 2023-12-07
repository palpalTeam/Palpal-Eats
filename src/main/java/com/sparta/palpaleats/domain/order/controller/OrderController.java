package com.sparta.palpaleats.domain.order.controller;

import com.sparta.palpaleats.domain.order.dto.OrderDto;
import com.sparta.palpaleats.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<String> createOrder(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody OrderDto.CreateOrderRequestDto requestDto
        )throws Exception{

        return ResponseEntity.ok()
                .body(orderService.createOrder(userDetails.getUser()),requestDto);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long orderId
        )throws Exception{

        return ResponseEntity.ok()
                .body(orderService.deleteOrder(userDetails.getUser()),orderId);
    }
}
