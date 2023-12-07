package com.sparta.palpaleats.domain.menu.service;

import com.sparta.palpaleats.domain.menu.dto.MenuRequestDto;
import com.sparta.palpaleats.domain.menu.entity.Menu;
import com.sparta.palpaleats.domain.menu.repository.MenuRepository;
import com.sparta.palpaleats.domain.s3.S3Service;
import com.sparta.palpaleats.domain.store.entity.Store;
import com.sparta.palpaleats.domain.store.repository.StoreRepository;
import com.sparta.palpaleats.global.common.CommonResponseCode;
import com.sparta.palpaleats.global.common.CommonResponseDto;
import com.sparta.palpaleats.global.exception.CustomException;
import com.sparta.palpaleats.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final S3Service s3Service;

    public CommonResponseDto addMenu(MenuRequestDto requestDto, Long id) throws UnsupportedEncodingException {
        Store store = findStore(id);
        Menu menu = new Menu();
        menu.setName(requestDto.getName());
        menu.setCategory(requestDto.getCategory());
        menu.setPrice(requestDto.getPrice());
        menu.setMenuPictureUrl(s3Service.saveFile(store.getName() + "/menu/" + requestDto.getName(), requestDto.getMenuPicture()));
        store.addMenuList(menu);
        menuRepository.save(menu);
        storeRepository.save(store);
        return new CommonResponseDto(CommonResponseCode.MENU_CREATE);
    }


    private Store findStore(Long id){
        return storeRepository.findById(id).orElseThrow(() ->
                new CustomException(ExceptionCode.NOT_FOUND_STORE));
    }
}
