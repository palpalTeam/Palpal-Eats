package com.sparta.palpaleats.domain.backoffice.service;

import com.sparta.palpaleats.domain.cart.entity.Cart;
import com.sparta.palpaleats.domain.cart.repository.CartRepository;
import com.sparta.palpaleats.domain.menu.entity.Menu;
import com.sparta.palpaleats.domain.menu.repository.MenuRepository;
import com.sparta.palpaleats.domain.order.dto.OrderDto;
import com.sparta.palpaleats.domain.order.entity.Order;
import com.sparta.palpaleats.domain.order.repository.OrderRepository;
import com.sparta.palpaleats.domain.store.entity.Store;
import com.sparta.palpaleats.domain.store.repository.StoreRepository;
import com.sparta.palpaleats.domain.user.entity.User;
import com.sparta.palpaleats.domain.user.entity.UserRoleEnum;
import com.sparta.palpaleats.global.dto.CommonResponseDto;
import com.sparta.palpaleats.global.exception.ExceptionCode;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BackOfficeService {

    private final CartRepository cartRepository;
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;

    public OrderDto.GetOrderResponseDto getOrder(User user,Long storeId, Long orderId) throws Exception{
        OrderDto.GetOrderResponseDto orderDto;
        List<OrderDto.GetCartResponseDto> cartDtoList = new ArrayList<>();

        Store store;
        Order order;
        List<Cart> cartList = new ArrayList<>();

        int errorCode=0;
        try{
            // user not seller
            if(user.getRole()!= UserRoleEnum.SELLER){
                errorCode = ExceptionCode.FORBIDDEN_YOUR_NOT_SELLER.getHttpStatus().value();
                throw new Exception(ExceptionCode.FORBIDDEN_YOUR_NOT_SELLER.getMessage());
            }

            // store not found
            store = storeRepository.findById(storeId)
                    .orElseThrow(()->new Exception(ExceptionCode.NOT_FOUND_STORE.getMessage()));
            // store is not user store
            if(!Objects.equals(store.getUser().getId(), user.getId())){
                errorCode = ExceptionCode.FORBIDDEN_YOUR_NOT_STORE_OWNER.getHttpStatus().value();
                throw new Exception(ExceptionCode.FORBIDDEN_YOUR_NOT_STORE_OWNER.getMessage());
            }

            // order not found
            order = orderRepository.findById(orderId)
                    .orElseThrow(()->new Exception(ExceptionCode.NOT_FOUND_ORDER.getMessage()));
            // order is not store order
            if(!Objects.equals(order.getStore().getId(),store.getId())){
                errorCode = ExceptionCode.FORBIDDEN_YOUR_NOT_STORE_ORDER.getHttpStatus().value();
                throw new Exception(ExceptionCode.FORBIDDEN_YOUR_NOT_STORE_ORDER.getMessage());
            }

            cartList = order.getCartList();

        }catch (Exception e){
            if(errorCode!=0){
                System.out.println("Error "+ errorCode +": " + e.getMessage());
            }else{
                System.out.println("Error 404: " + e.getMessage());
            }
            return null;
        }

        for(Cart cart:cartList){
            OrderDto.GetCartResponseDto cartDto = OrderDto.GetCartResponseDto.builder()
                    .menuName(cart.getMenu().getName())
                    .quantity(cart.getQuantity())
                    .totalPrice((long)cart.getMenu().getPrice()*(long)cart.getQuantity())
                    .build();

            cartDtoList.add(cartDto);
        }

        orderDto = OrderDto.GetOrderResponseDto.builder()
                .orderId(orderId)
                .orderStatus(order.getOrderStatus())
                .storeName(store.getName())
                .createdAt(order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .deliveryAddress(order.getDeliveryAddress())
                .paymentMethod(order.getPaymentMethod())
                .requestMsg(order.getRequests())
                .totalPrice(order.getTotalPrice())
                .cartList(cartDtoList)
                .build();

        return orderDto;
    }

    public List<OrderDto.GetOrderListResponseDto> getOrderList(User user,Long storeId) throws Exception{
        List<OrderDto.GetOrderListResponseDto> orderDtoList = new ArrayList<>();

        Store store;
        List<Order> orderList = new ArrayList<>();

        int errorCode=0;
        try{
            // user not seller
            if(user.getRole()!= UserRoleEnum.SELLER){
                errorCode = ExceptionCode.FORBIDDEN_YOUR_NOT_SELLER.getHttpStatus().value();
                throw new Exception(ExceptionCode.FORBIDDEN_YOUR_NOT_SELLER.getMessage());
            }

            // store not found
            store = storeRepository.findById(storeId)
                    .orElseThrow(()->new Exception(ExceptionCode.NOT_FOUND_STORE.getMessage()));
            // store is not user store
            if(!Objects.equals(store.getUser().getId(), user.getId())){
                errorCode = ExceptionCode.FORBIDDEN_YOUR_NOT_STORE_OWNER.getHttpStatus().value();
                throw new Exception(ExceptionCode.FORBIDDEN_YOUR_NOT_STORE_OWNER.getMessage());
            }

            orderList = store.getOrederList();

        }catch (Exception e){
            if(errorCode!=0){
                System.out.println("Error "+ errorCode +": " + e.getMessage());
            }else{
                System.out.println("Error 404: " + e.getMessage());
            }
            return null;
        }

        for(Order order:orderList){
            OrderDto.GetOrderListResponseDto orderDto = OrderDto.GetOrderListResponseDto.builder()
                    .orderId(order.getId())
                    .orderStatus(order.getOrderStatus())
                    .storeName(store.getName())
                    .createdAt(order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .deliveryAddress(order.getDeliveryAddress())
                    .paymentMethod(order.getPaymentMethod())
                    .requestMsg(order.getRequests())
                    .totalPrice(order.getTotalPrice())
                    .cartMsg(order.getFirstItemAndOthersCount())
                    .build();

            orderDtoList.add(orderDto);
        }

        return orderDtoList;
    }
}
