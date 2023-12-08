package com.sparta.palpaleats.domain.menu.controller;

import com.sparta.palpaleats.domain.menu.dto.MenuRequestDto;
import com.sparta.palpaleats.domain.menu.dto.MenuResponseDto;
import com.sparta.palpaleats.domain.menu.service.MenuService;
import com.sparta.palpaleats.domain.store.dto.StoreResponseDto;
import com.sparta.palpaleats.global.common.CommonResponseCode;
import com.sparta.palpaleats.global.common.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/{storeId}/menu")
    public ResponseEntity<CommonResponseDto> addMenu(@ModelAttribute MenuRequestDto requestDto, @PathVariable Long storeId) throws UnsupportedEncodingException {
        CommonResponseDto commonResponseDto = menuService.addMenu(requestDto, storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @GetMapping("/{storeId}/menu")
    public ResponseEntity<List<MenuResponseDto>> getMenuList(@PathVariable Long storeId){
        List<MenuResponseDto> menuResponseDtoList = menuService.getMenuList(storeId);
        return ResponseEntity.ok().body(menuResponseDtoList);
    }

    @GetMapping("{storeId}/menu/{menuId}")
    public ResponseEntity<MenuResponseDto> getMenu(@PathVariable Long storeId, @PathVariable Long menuId){
        MenuResponseDto menuResponseDto = menuService.getMenu(storeId, menuId);
        return ResponseEntity.ok().body(menuResponseDto);
    }

    @PatchMapping("{storeId}/menu/{menuId}")
    public ResponseEntity<CommonResponseDto> updateMenuPicture(@PathVariable Long storeId, @PathVariable Long menuId, @RequestParam("file") MultipartFile multipartFile) throws UnsupportedEncodingException {
        CommonResponseDto commonResponseDto = menuService.updateMenuPicture(storeId,menuId, multipartFile);
        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PatchMapping("{storeId}/menu/{menuId}")
    public ResponseEntity<CommonResponseDto> updateMenuName(@PathVariable Long storeId, @PathVariable Long menuId, @RequestParam("name") String name){
        CommonResponseDto commonResponseDto = menuService.updateMenuName(storeId, menuId, name);
        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PatchMapping("{storeId}/menu/{menuId}")
    public ResponseEntity<CommonResponseDto> updateMenuPrice(@PathVariable Long storeId, @PathVariable Long menuId, @RequestParam("price") Integer price){
        CommonResponseDto commonResponseDto = menuService.updateMenuPrice(storeId, menuId, price);
        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PatchMapping("{storeId}/menu/{menuId}")
    public ResponseEntity<CommonResponseDto> updateMenuCategory(@PathVariable Long storeId, @PathVariable Long menuId, @RequestParam("category") String category){
        CommonResponseDto commonResponseDto = menuService.updateMenuCategory(storeId, menuId, category);
        return ResponseEntity.ok().body(commonResponseDto);
    }
}