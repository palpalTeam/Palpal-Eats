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
import com.sparta.palpaleats.domain.order.repository.OrderRepository;
import com.sparta.palpaleats.domain.store.entity.Store;
import com.sparta.palpaleats.domain.store.repository.StoreRepository;
import com.sparta.palpaleats.domain.user.entity.User;
import com.sparta.palpaleats.global.dto.CommonResponseDto;
import com.sparta.palpaleats.global.exception.ExceptionCode;
import java.util.List;
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

        List<Cart> cartList = cartRepository.findAllByUserId(user.getId());

        if(cartList.isEmpty()){
            return new CommonResponseDto(HttpStatus.NOT_FOUND.value()
                    , ExceptionCode.NOT_FOUND_CART.getMessage());
        }

        try{
            for(Cart cart:cartList){
                if(!cartRepository.existsById(cart.getId())){
                    throw new Exception(ExceptionCode.NOT_FOUND_CART.getMessage());
                }
                if(cart.getMenu()==null){
                    throw new Exception(ExceptionCode.NOT_FOUND_MENU.getMessage());
                }
                if(!menuRepository.existsById(cart.getMenu().getId())){
                    throw new Exception(ExceptionCode.NOT_FOUND_MENU.getMessage());
                }
                if(cart.getMenu().getStore()==null){
                    throw new Exception(ExceptionCode.NOT_FOUND_MENU.getMessage());
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
                .user(user)
                .store(cartList.get(0).getMenu().getStore())
                .cartList(cartList)
                .build();

        orderRepository.save(order);

        for(Cart cart:cartList){
            cartService.deleteCart(user,cart.getId());
        }

       return new CommonResponseDto(HttpStatus.OK.value(), "주문 생 완료");
    }

    public CommonResponseDto deleteOrder(User user, Long orderId) throws Exception{

        try{
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(()->new Exception(ExceptionCode.NOT_FOUND_ORDER.getMessage()));
        }catch (Exception e){
            return new CommonResponseDto(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }

        orderRepository.deleteById(orderId);

        return new CommonResponseDto(HttpStatus.OK.value(), "주문 삭제 완료");
    }
}
