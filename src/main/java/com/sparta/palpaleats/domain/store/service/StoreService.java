package com.sparta.palpaleats.domain.store.service;

import com.sparta.palpaleats.domain.review.entity.Review;
import com.sparta.palpaleats.domain.s3.S3Service;
import com.sparta.palpaleats.domain.store.dto.StoreResponseDto;
import com.sparta.palpaleats.global.common.CommonResponseCode;
import com.sparta.palpaleats.global.common.CommonResponseDto;
import com.sparta.palpaleats.domain.store.dto.StoreRequestDto;
import com.sparta.palpaleats.domain.store.entity.Store;
import com.sparta.palpaleats.domain.store.repository.StoreRepository;
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

    public CommonResponseDto addStore(StoreRequestDto requestDto) throws UnsupportedEncodingException {
        Store store = new Store();
        store.setName(requestDto.getName());
        store.setCategory(requestDto.getCategory());
        store.setAddress(requestDto.getAddress());
        store.setContent(requestDto.getContent());
        store.setPhone(requestDto.getPhone());
        store.setMinDeliveryPrice(requestDto.getMinDeliveryPrice());
        store.setStorePictureUrl(s3Service.saveFile(requestDto.getName() + "/store", requestDto.getStorePicture()));
        store.setOpenStatus(requestDto.isOpenStatus());

        storeRepository.save(store);
        return new CommonResponseDto(CommonResponseCode.STORE_CREATE);
    }

    @Transactional
    public CommonResponseDto updateStorePicture(MultipartFile multipartFile, Long id) throws UnsupportedEncodingException {
        Store store = findStore(id);
        s3Service.deleteImage(store.getStorePictureUrl());
        store.updatePicture(s3Service.saveFile(store.getName(), multipartFile));
        return new CommonResponseDto(CommonResponseCode.STORE_SET_IMAGE);
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

    private StoreResponseDto convertStoreResponseDto(Store store){
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
