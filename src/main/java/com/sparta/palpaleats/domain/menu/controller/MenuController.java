package com.sparta.palpaleats.domain.menu.controller;

import com.sparta.palpaleats.domain.menu.dto.MenuRequestDto;
import com.sparta.palpaleats.domain.menu.service.MenuService;
import com.sparta.palpaleats.domain.store.dto.StoreRequestDto;
import com.sparta.palpaleats.global.common.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

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
}
