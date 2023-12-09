package com.sparta.palpaleats.domain.order.service;

import com.sparta.palpaleats.domain.cart.controller.CartController;
import com.sparta.palpaleats.domain.cart.dto.CartDto;
import com.sparta.palpaleats.domain.cart.entity.Cart;
import com.sparta.palpaleats.domain.cart.repository.CartRepository;
import com.sparta.palpaleats.domain.cart.service.CartService;
import com.sparta.palpaleats.domain.menu.entity.Menu;
import com.sparta.palpaleats.domain.menu.repository.MenuRepository;
import com.sparta.palpaleats.domain.order.dto.OrderDto;
import com.sparta.palpaleats.domain.order.entity.Order;
import com.sparta.palpaleats.domain.order.enums.OrderStatus;
import com.sparta.palpaleats.domain.order.repository.OrderRepository;
import com.sparta.palpaleats.domain.store.entity.Store;
import com.sparta.palpaleats.domain.store.repository.StoreRepository;
import com.sparta.palpaleats.domain.user.entity.User;
import com.sparta.palpaleats.global.dto.CommonResponseDto;
import com.sparta.palpaleats.global.exception.ExceptionCode;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final CartService cartService;

    public CommonResponseDto createOrder(User user,
            OrderDto.CreateOrderRequestDto requestDto) throws Exception{

        List<Cart> cartList = cartRepository.findAllByUserIdAndOrderIdIsNull(user.getId());

        if(cartList.isEmpty()){
            return new CommonResponseDto(HttpStatus.NOT_FOUND.value()
                    , ExceptionCode.NOT_FOUND_CART.getMessage());
        }

        try{
            for(Cart cart:cartList){
                // cart not found
                if(!cartRepository.existsById(cart.getId())){
                    throw new Exception(ExceptionCode.NOT_FOUND_CART.getMessage());
                }

                // menu not found
                if(cart.getMenu()==null){
                    throw new Exception(ExceptionCode.NOT_FOUND_MENU.getMessage());
                }
                Menu menu = menuRepository.findById(cart.getMenu().getId())
                        .orElseThrow(()->new Exception(ExceptionCode.NOT_FOUND_MENU.getMessage()));
                if(menu.isDeleted()){
                    throw new Exception(ExceptionCode.NOT_FOUND_MENU.getMessage());
                }

                // store not found
                if(cart.getMenu().getStore()==null){
                    throw new Exception(ExceptionCode.NOT_FOUND_STORE.getMessage());
                }
                if(!storeRepository.existsById(cart.getMenu().getStore().getId())){
                    throw new Exception(ExceptionCode.NOT_FOUND_STORE.getMessage());
                }
            }
        }catch (Exception e){
            return new CommonResponseDto(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }

        long totalPrice = 0;
        for(Cart cart:cartList){
            totalPrice += (long)cart.getMenu().getPrice() * (long)cart.getQuantity();
        }

        Order order = Order.builder()
                .deliveryAddress(requestDto.getDeliveryAddress())
                .paymentMethod(requestDto.getPaymentMethod())
                .requests(requestDto.getRequestMsg())
                .totalPrice(totalPrice)
                .orderStatus(OrderStatus.ORDER_START.getStatus())
                .user(user)
                .store(cartList.get(0).getMenu().getStore())
                .cartList(cartList)
                .build();

        Order saveOrder = orderRepository.save(order);

        for(Cart cart:cartList){
            cart.setOrder(saveOrder);
            cartRepository.save(cart);
        }

       return new CommonResponseDto(HttpStatus.OK.value(), "주문 생성 완료");
    }

    public CommonResponseDto deleteOrder(User user, Long orderId) throws Exception{

        Order order;
        try{
            order = orderRepository.findById(orderId)
                    .orElseThrow(()->new Exception(ExceptionCode.NOT_FOUND_ORDER.getMessage()));
        }catch (Exception e){
            return new CommonResponseDto(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }

        try{
            if(order.isDeleted()){
                throw new Exception(ExceptionCode.CONFLICT_ORDER_ALREADY_CANCELED.getMessage());
            }
        }catch (Exception e){
            return new CommonResponseDto(HttpStatus.CONFLICT.value(), e.getMessage());
        }

        try{
            if(!Objects.equals(order.getUser().getId(), user.getId())){
                throw new Exception(ExceptionCode.FORBIDDEN_YOUR_NOT_COME_IN.getMessage());
            }
        }catch (Exception e){
            return new CommonResponseDto(HttpStatus.FORBIDDEN.value(), e.getMessage());
        }

        order.setDeleted(true);
        order.setOrderStatus(OrderStatus.ORDER_CANCELED.getStatus());
        orderRepository.save(order);

        return new CommonResponseDto(HttpStatus.OK.value(), "주문 취소 완료");
    }
}
