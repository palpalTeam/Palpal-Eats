package com.sparta.palpaleats.domain.menu.service;

import com.sparta.palpaleats.domain.menu.dto.MenuRequestDto;
import com.sparta.palpaleats.domain.menu.dto.MenuResponseDto;
import com.sparta.palpaleats.domain.menu.entity.Menu;
import com.sparta.palpaleats.domain.menu.repository.MenuRepository;
import com.sparta.palpaleats.domain.s3.S3Service;
import com.sparta.palpaleats.domain.s3.S3Util;
import com.sparta.palpaleats.domain.store.entity.Store;
import com.sparta.palpaleats.domain.store.repository.StoreRepository;
import com.sparta.palpaleats.global.common.CommonResponseCode;
import com.sparta.palpaleats.global.common.CommonResponseDto;
import com.sparta.palpaleats.global.exception.CustomException;
import com.sparta.palpaleats.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    private final S3Service s3Service;
    private final S3Util s3Util;

    public CommonResponseDto addMenu(MenuRequestDto requestDto, Long id) throws UnsupportedEncodingException {
        Store store = findStore(id);
        Menu menu = new Menu();
        menu.setName(requestDto.getName());
        menu.setCategory(requestDto.getCategory());
        menu.setPrice(requestDto.getPrice());
        if (s3Util.validateFileExists(requestDto.getMenuPicture())) {
            String[] urlArr = s3Service.saveFile(store.getName() + "/menu/" + requestDto.getName(), requestDto.getMenuPicture());
            menu.setMenuPictureUrl(urlArr[0]);
            menu.setMenuPicturePath(urlArr[1]);
        }
        store.addMenuList(menu);
        menuRepository.save(menu);
        storeRepository.save(store);
        return new CommonResponseDto(CommonResponseCode.MENU_CREATE);
    }


    public List<MenuResponseDto> getMenuList(Long storeId) {
        return menuRepository.findAllByStoreId(storeId).stream().map(MenuResponseDto::new).toList();
    }








    private Store findStore(Long id){
        return storeRepository.findById(id).orElseThrow(() ->
                new CustomException(ExceptionCode.NOT_FOUND_STORE));
    }
}