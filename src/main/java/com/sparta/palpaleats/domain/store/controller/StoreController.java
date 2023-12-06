package com.sparta.palpaleats.domain.store.controller;

import com.sparta.palpaleats.domain.store.dto.CommonResponseDto;
import com.sparta.palpaleats.domain.store.dto.StoreRequestDto;
import com.sparta.palpaleats.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    @PostMapping
    public ResponseEntity<CommonResponseDto> addStore(@RequestBody StoreRequestDto storeRequestDto)
    {
        CommonResponseDto commonResponseDto = storeService.addStore(storeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @PatchMapping("/picture")
    public ResponseEntity<CommonResponseDto> addStorePicture(@RequestParam("file") MultipartFile multipartFile, @RequestParam("storeId") Long storeId) throws IOException {
        CommonResponseDto commonResponseDto = storeService.addStorePicture(multipartFile, storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }
}
