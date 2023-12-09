package com.sparta.palpaleats.domain.cart.service;

import com.sparta.palpaleats.domain.cart.dto.CartDto;
import com.sparta.palpaleats.domain.cart.entity.Cart;
import com.sparta.palpaleats.domain.cart.repository.CartRepository;
import com.sparta.palpaleats.domain.menu.entity.Menu;
import com.sparta.palpaleats.domain.menu.repository.MenuRepository;
import com.sparta.palpaleats.domain.store.entity.Store;
import com.sparta.palpaleats.domain.store.repository.StoreRepository;
import com.sparta.palpaleats.domain.user.entity.User;
import com.sparta.palpaleats.global.dto.CommonResponseDto;
import com.sparta.palpaleats.global.exception.ExceptionCode;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    public CommonResponseDto createCart(User user,
            CartDto.CreateCartRequestDto requestDto) throws Exception{

        List<Cart> cartList = cartRepository.findAllByUserIdAndOrderIdIsNull(user.getId());
        Menu menu;
        Store originStore;
        Store newStore;
        if(!cartList.isEmpty()){
            originStore=cartList.get(0).getMenu().getStore();
        }

        try{
            menu = menuRepository.findById(requestDto.getMenuId())
                    .orElseThrow(()->new Exception(ExceptionCode.NOT_FOUND_MENU.getMessage()));
            if(menu.getStore()==null){
                throw new Exception(ExceptionCode.NOT_FOUND_STORE.getMessage());
            }
            newStore = storeRepository.findById(menu.getStore().getId())
                    .orElseThrow(()->new Exception(ExceptionCode.NOT_FOUND_STORE.getMessage()));
        }catch (Exception e){
            return new CommonResponseDto(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }

        if(!cartList.isEmpty()){
            originStore=cartList.get(0).getMenu().getStore();
            if(!originStore.getId().equals(newStore.getId())){
                for(Cart cart:cartList){
                    deleteCart(user,cart.getId());
                }
            }
        }

        Cart cart = Cart.builder()
                .quantity(requestDto.getQuantity())
                .user(user)
                .menu(menu)
                .build();
        cartRepository.save(cart);

        return new CommonResponseDto(HttpStatus.OK.value(), "장바구니 생성 완료");
    }

    public CommonResponseDto deleteCart(User user, Long cartId) throws Exception{

        Cart cart;

        try{
            cart = cartRepository.findById(cartId)
                    .orElseThrow(()->new Exception(ExceptionCode.NOT_FOUND_CART.getMessage()));
            if(cart.getOrder()!=null){
                throw new Exception(ExceptionCode.CONFLICT_CART_ALREADY_ORDERED.getMessage());
            }
        }catch (Exception e){
            return new CommonResponseDto(HttpStatus.NOT_FOUND.value(),e.getMessage());
        }

        cartRepository.delete(cart);

        return new CommonResponseDto(HttpStatus.OK.value(), "장바구니 삭제 완료");
    }

    public CommonResponseDto updateCart(User user, Long cartId,
            CartDto.UpdateCartRequestDto requestDto) throws Exception{

        Cart cart;

        try{
            cart = cartRepository.findById(cartId)
                    .orElseThrow(()->new Exception(ExceptionCode.NOT_FOUND_CART.getMessage()));
        }catch (Exception e){
            return new CommonResponseDto(HttpStatus.NOT_FOUND.value(),e.getMessage());
        }

        cart.setQuantity(requestDto.getQuantity());
        cartRepository.save(cart);

        return new CommonResponseDto(HttpStatus.OK.value(), "장바구니 업데이트 완료");
    }

    public CommonResponseDto getCart(User user) throws Exception{
        List<CartDto.GetCartResponseDto> cartDtoList = new ArrayList<>();
        List<Cart> cartList = cartRepository.findAllByUserIdAndOrderIdIsNull(user.getId());

        for(Cart cart:cartList){
            Menu menu;
            Store store;
            try{
                // store not found
                if(cart.getMenu()==null){
                    throw new Exception(ExceptionCode.NOT_FOUND_MENU.getMessage());
                }
                menu = menuRepository.findById(cart.getMenu().getId())
                        .orElseThrow(()->new Exception(ExceptionCode.NOT_FOUND_MENU.getMessage()));

                // store not found
                if(menu.getStore()==null){
                    throw new Exception(ExceptionCode.NOT_FOUND_STORE.getMessage());
                }
                store = storeRepository.findById(menu.getStore().getId())
                        .orElseThrow(()->new Exception(ExceptionCode.NOT_FOUND_STORE.getMessage()));
            }catch (Exception e){
                return new CommonResponseDto(HttpStatus.NOT_FOUND.value(), e.getMessage());
            }

            CartDto.GetCartResponseDto dto = CartDto.GetCartResponseDto.builder()
                    .storeName(store.getName())
                    .menuName(menu.getName())
                    .quantity(cart.getQuantity())
                    .totalPrice(menu.getPrice()*(long)cart.getQuantity())
                    .build();

            cartDtoList.add(dto);
        }

        return new CommonResponseDto(HttpStatus.OK.value(), cartDtoList);
    }
}
