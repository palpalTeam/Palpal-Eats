package com.sparta.palpaleats.domain.backoffice.controller;

import com.sparta.palpaleats.domain.backoffice.service.BackOfficeService;
import com.sparta.palpaleats.domain.order.dto.OrderDto;
import com.sparta.palpaleats.global.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("backoffice")
@RequiredArgsConstructor
public class BackOfficeController {

    private final BackOfficeService backOfficeService;

    @GetMapping("/stores/{storeId}/orders/{orderId}")
    public ResponseEntity<OrderDto.GetOrderResponseDto> getOrder(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long storeId,
            @PathVariable Long orderId
    )throws Exception{

        return ResponseEntity.ok()
                .body(backOfficeService.getOrder(userDetails.getUser(),storeId,orderId));
    }

    @GetMapping("/stores/{storeId}/orders")
    public ResponseEntity<List<OrderDto.GetOrderListResponseDto>> getOrderList(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long storeId
    )throws Exception{

        return ResponseEntity.ok()
                .body(backOfficeService.getOrderList(userDetails.getUser(),storeId));
    }
}
