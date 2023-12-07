package com.sparta.palpaleats.domain.store.controller;

import com.sparta.palpaleats.global.common.CommonResponseDto;
import com.sparta.palpaleats.domain.store.dto.StoreRequestDto;
import com.sparta.palpaleats.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    @PostMapping
    public ResponseEntity<CommonResponseDto> addStore(@ModelAttribute StoreRequestDto requestDto) throws UnsupportedEncodingException {
        CommonResponseDto commonResponseDto = storeService.addStore(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @PatchMapping("/picture")
    public ResponseEntity<CommonResponseDto> updateStorePicture(@RequestParam("file") MultipartFile multipartFile, @RequestParam("storeId") Long storeId) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStorePicture(multipartFile, storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

}
