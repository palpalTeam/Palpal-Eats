package com.sparta.palpaleats.domain.store.service;

import com.sparta.palpaleats.domain.s3.S3Service;
import com.sparta.palpaleats.domain.s3.S3Util;
import com.sparta.palpaleats.domain.store.dto.StoreRequestDto;
import com.sparta.palpaleats.domain.store.dto.StoreResponseDto;
import com.sparta.palpaleats.domain.store.entity.Store;
import com.sparta.palpaleats.domain.store.repository.StoreRepository;
import com.sparta.palpaleats.global.common.CommonResponseCode;
import com.sparta.palpaleats.global.common.CommonResponseDto;
import com.sparta.palpaleats.global.exception.CustomException;
import com.sparta.palpaleats.global.exception.ExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final S3Service s3Service;

    private final S3Util s3Util;

    public CommonResponseDto addStore(StoreRequestDto requestDto) throws UnsupportedEncodingException {
        Store store = new Store();
        store.setName(requestDto.getName());
        store.setCategory(requestDto.getCategory());
        store.setAddress(requestDto.getAddress());
        store.setContent(requestDto.getContent());
        store.setPhone(requestDto.getPhone());
        store.setMinDeliveryPrice(requestDto.getMinDeliveryPrice());
        if (s3Util.validateFileExists(requestDto.getStorePicture())) {
            String[] urlArr = s3Service.saveFile(requestDto.getName() + "/store", requestDto.getStorePicture());
            store.setStorePictureUrl(urlArr[0]);
            store.setStorePicturePath(urlArr[1]);
        }
        store.setOpenStatus(requestDto.isOpenStatus());
        storeRepository.save(store);
        return new CommonResponseDto(CommonResponseCode.STORE_CREATE);
    }


    @Transactional
    public CommonResponseDto updatePicture(MultipartFile multipartFile, Long storeId) throws UnsupportedEncodingException {
        Store store = findStore(storeId);
        if (s3Util.validateFileExists(multipartFile)) {
            s3Service.deleteImage(store.getStorePicturePath());
            String[] urlArr = s3Service.saveFile(store.getName() + "/store", multipartFile);
            store.updatePicture(urlArr);
        }
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }


    @Transactional
    public CommonResponseDto updateStoreName(String name, Long storeId) {
        Store store = findStore(storeId);
        store.setName(name);
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }

    @Transactional
    public CommonResponseDto updateStoreCategory(String category, Long storeId) {
        Store store = findStore(storeId);
        store.setCategory(category);
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }

    @Transactional
    public CommonResponseDto updateStoreAddress(String address, Long storeId){
        Store store = findStore(storeId);
        store.setAddress(address);
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }


    @Transactional
    public CommonResponseDto updateStorePhone(String phone, Long storeId) {
        Store store = findStore(storeId);
        store.setPhone(phone);
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }


    @Transactional
    public CommonResponseDto updateStoreContent(String content, Long storeId) {
        Store store = findStore(storeId);
        store.setContent(content);
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }

    @Transactional
    public CommonResponseDto updateStoreMinDeliveryPrice(Integer minDeliveryPrice, Long storeId) {
        Store store = findStore(storeId);
        store.setMinDeliveryPrice(minDeliveryPrice);
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }

    @Transactional
    public CommonResponseDto updateStoreOpenStatus(Boolean openStatus, Long storeId) {
        Store store = findStore(storeId);
        store.setOpenStatus(openStatus);
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }

    public List<StoreResponseDto> getStoreList() {
        return storeRepository.findAll().stream().map(this::convertStoreResponseDto).toList();
    }


    public StoreResponseDto getStore(Long storeId) {
        Store store = findStore(storeId);
        return convertStoreResponseDto(store);
    }
    private Store findStore(Long id) {
        return storeRepository.findById(id).orElseThrow(() ->
                new CustomException(ExceptionCode.NOT_FOUND_STORE));
    }

    private StoreResponseDto convertStoreResponseDto(Store store) {
        StoreResponseDto storeResponseDto = new StoreResponseDto();
        storeResponseDto.setName(store.getName());
        storeResponseDto.setCategory(store.getCategory());
        storeResponseDto.setContent(store.getContent());
        storeResponseDto.setStorePictureUrl(store.getStorePictureUrl());
        storeResponseDto.setReviewRatingAvg(store.getAverageReviewRating());
        storeResponseDto.setReviewCount(store.getReviewCount());
        storeResponseDto.setOpenStatus(store.isOpenStatus());
        return storeResponseDto;
    }
}