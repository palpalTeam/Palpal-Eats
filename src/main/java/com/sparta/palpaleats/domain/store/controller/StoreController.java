package com.sparta.palpaleats.domain.store.controller;

import com.sparta.palpaleats.domain.store.dto.StoreResponseDto;
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
import java.util.List;

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

    @PatchMapping("/{storeId}/picture")
    public ResponseEntity<CommonResponseDto> updateStorePicture(@RequestParam("file") MultipartFile multipartFile, @PathVariable Long storeId) throws UnsupportedEncodingException {
        CommonResponseDto commonResponseDto = storeService.updatePicture(multipartFile, storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @PatchMapping("/{storeId}/name")
    public ResponseEntity<CommonResponseDto> updateStoreName(@RequestParam("name") String name, @PathVariable Long storeId) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStoreName(name, storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @PatchMapping("/{storeId}/category")
    public ResponseEntity<CommonResponseDto> updateStoreCategory(@RequestParam("category") String category, @PathVariable Long storeId) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStoreCategory(category, storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @PatchMapping("/{storeId}/address")
    public ResponseEntity<CommonResponseDto> updateStoreAddress(@RequestParam("address") String address, @PathVariable Long storeId) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStoreAddress(address, storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @PatchMapping("/{storeId}/phone")
    public ResponseEntity<CommonResponseDto> updateStorePhone(@RequestParam("phone") String phone, @PathVariable Long storeId) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStorePhone(phone, storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @PatchMapping("/{storeId}/content")
    public ResponseEntity<CommonResponseDto> updateStoreContent(@RequestParam("content") String content, @PathVariable Long storeId) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStoreContent(content, storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }
    @PatchMapping("/{storeId}/min_delivery_price")
    public ResponseEntity<CommonResponseDto> updateStoreMinDeliveryPrice(@RequestParam("min_delivery_price") Integer minDeliveryPrice, @PathVariable Long storeId) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStoreMinDeliveryPrice(minDeliveryPrice, storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @PatchMapping("/{storeId}/open_status")
    public ResponseEntity<CommonResponseDto> updateStoreOpenStatus(@RequestParam("open_status") Boolean openStatus, @PathVariable Long storeId) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStoreOpenStatus(openStatus, storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<StoreResponseDto>> getStoreList() {
        List<StoreResponseDto> storeResponseDtoList = storeService.getStoreList();
        return ResponseEntity.ok().body(storeResponseDtoList);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto> getStore(@PathVariable Long storeId) {
        StoreResponseDto storeResponseDto = storeService.getStore(storeId);
        return ResponseEntity.ok().body(storeResponseDto);
    }

//    @DeleteMapping("/{storeId}")
//    public ResponseEntity<CommonResponseDto> deleteStore(@PathVariable Long storeId) {
//        CommonResponseDto commonResponseDto = storeService.deleteStore(storeId);
//        return ResponseEntity.ok().body(commonResponseDto);
//    }
}
