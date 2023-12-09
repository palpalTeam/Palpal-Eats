package com.sparta.palpaleats.domain.store.controller;

import com.sparta.palpaleats.domain.store.dto.StoreResponseDto;
import com.sparta.palpaleats.global.common.CommonResponseDto;
import com.sparta.palpaleats.domain.store.dto.StoreRequestDto;
import com.sparta.palpaleats.domain.store.service.StoreService;
import com.sparta.palpaleats.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<CommonResponseDto> addStore(
            @ModelAttribute StoreRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) throws UnsupportedEncodingException {
        CommonResponseDto commonResponseDto = storeService.addStore(userDetails.getUser().getId(), requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @PatchMapping("/{storeId}/picture")
    public ResponseEntity<CommonResponseDto> updateStorePicture(@RequestParam("file") MultipartFile multipartFile,
                                                                @PathVariable Long storeId,
                                                                @AuthenticationPrincipal UserDetailsImpl userDetails) throws UnsupportedEncodingException {
        CommonResponseDto commonResponseDto = storeService.updatePicture(multipartFile, storeId,userDetails.getUser().getId());
        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PatchMapping("/{storeId}/name")
    public ResponseEntity<CommonResponseDto> updateStoreName(@RequestParam("name") String name,
                                                             @PathVariable Long storeId,
                                                             @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStoreName(name, storeId, userDetails.getUser().getId());
        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PatchMapping("/{storeId}/category")
    public ResponseEntity<CommonResponseDto> updateStoreCategory(@RequestParam("category") String category,
                                                                 @PathVariable Long storeId,
                                                                 @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStoreCategory(category, storeId, userDetails.getUser().getId());
        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PatchMapping("/{storeId}/address")
    public ResponseEntity<CommonResponseDto> updateStoreAddress(@RequestParam("address") String address,
                                                                @PathVariable Long storeId,
                                                                @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStoreAddress(address, storeId,userDetails.getUser().getId());
        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PatchMapping("/{storeId}/phone")
    public ResponseEntity<CommonResponseDto> updateStorePhone(@RequestParam("phone") String phone,
                                                              @PathVariable Long storeId,
                                                              @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStorePhone(phone, storeId,userDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @PatchMapping("/{storeId}/content")
    public ResponseEntity<CommonResponseDto> updateStoreContent(@RequestParam("content") String content, @PathVariable Long storeId,
                                                                @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStoreContent(content, storeId, userDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }
    @PatchMapping("/{storeId}/min_delivery_price")
    public ResponseEntity<CommonResponseDto> updateStoreMinDeliveryPrice(@RequestParam("min_delivery_price") Integer minDeliveryPrice, @PathVariable Long storeId,
                                                                         @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStoreMinDeliveryPrice(minDeliveryPrice, storeId, userDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @PatchMapping("/{storeId}/open_status")
    public ResponseEntity<CommonResponseDto> updateStoreOpenStatus(@RequestParam("open_status") Boolean openStatus, @PathVariable Long storeId,
                                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        CommonResponseDto commonResponseDto = storeService.updateStoreOpenStatus(openStatus, storeId, userDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponseDto);
    }

    @GetMapping("/total")
    public ResponseEntity<List<StoreResponseDto>> getTotalStoreList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<StoreResponseDto> storeResponseDtoList = storeService.getTotalStoreList(userDetails.getUser().getId());
        return ResponseEntity.ok().body(storeResponseDtoList);
    }

    //Seller 입장에서 가능
    @GetMapping("/user")
    public ResponseEntity<List<StoreResponseDto>> getStoreList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<StoreResponseDto> storeResponseDtoList = storeService.getStoreList(userDetails.getUser().getId());
        return ResponseEntity.ok().body(storeResponseDtoList);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto> getStore(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long storeId) {
        StoreResponseDto storeResponseDto = storeService.getStore(userDetails.getUser().getId() ,storeId);
        return ResponseEntity.ok().body(storeResponseDto);
    }

    @PatchMapping("{storeId}/delete")
    public ResponseEntity<CommonResponseDto> deleteStore(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long storeId){
        CommonResponseDto commonResponseDto = storeService.deleteStore(userDetails.getUser().getId(), storeId);
        return ResponseEntity.ok().body(commonResponseDto);
    }

}
