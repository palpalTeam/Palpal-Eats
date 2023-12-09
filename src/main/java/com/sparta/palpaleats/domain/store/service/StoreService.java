package com.sparta.palpaleats.domain.store.service;

import com.sparta.palpaleats.domain.s3.S3Service;
import com.sparta.palpaleats.domain.s3.S3Util;
import com.sparta.palpaleats.domain.store.dto.StoreRequestDto;
import com.sparta.palpaleats.domain.store.dto.StoreResponseDto;
import com.sparta.palpaleats.domain.store.entity.Store;
import com.sparta.palpaleats.domain.store.repository.StoreRepository;
import com.sparta.palpaleats.domain.user.entity.User;
import com.sparta.palpaleats.domain.user.repository.UserRepository;
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
import java.util.Map;

import static com.sparta.palpaleats.domain.user.entity.UserRoleEnum.SELLER;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final S3Service s3Service;
    private final UserRepository userRepository;
    private final S3Util s3Util;

    public CommonResponseDto addStore(Long id, StoreRequestDto requestDto) throws UnsupportedEncodingException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );
        if(!user.getRole().equals(SELLER)){
            throw new CustomException(ExceptionCode.FORBIDDEN_YOUR_NOT_SELLER);
        }
        Store store = new Store();
        store.setName(requestDto.getName());
        store.setCategory(requestDto.getCategory());
        store.setAddress(requestDto.getAddress());
        store.setContent(requestDto.getContent());
        store.setPhone(requestDto.getPhone());
        store.setMinDeliveryPrice(requestDto.getMinDeliveryPrice());
        if (s3Util.validateFileExists(requestDto.getStorePicture())) {
            String[] urlArr = s3Service.saveFile(requestDto.getName(), requestDto.getStorePicture());
            store.setStorePictureUrl(urlArr[0]);
            store.setStorePicturePath(urlArr[1]);
        }
        store.setOpenStatus(requestDto.isOpenStatus());
        store.setUser(user);
        user.addStoreList(store);
        storeRepository.save(store);
        return new CommonResponseDto(CommonResponseCode.STORE_CREATE);
    }


    @Transactional
    public CommonResponseDto updatePicture(MultipartFile multipartFile, Long storeId, Long id) throws UnsupportedEncodingException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );
        if(!user.getRole().equals(SELLER)){
            throw new CustomException(ExceptionCode.FORBIDDEN_YOUR_NOT_SELLER);
        }
        Store store = findStore(storeId);
        if (s3Util.validateFileExists(multipartFile)) {
            s3Service.deleteImage(store.getStorePicturePath());
            String[] urlArr = s3Service.saveFile(store.getName(), multipartFile);
            store.updatePicture(urlArr);
        }
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }


    @Transactional
    public CommonResponseDto updateStoreName(String name, Long storeId, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );
        if(!user.getRole().equals(SELLER)){
            throw new CustomException(ExceptionCode.FORBIDDEN_YOUR_NOT_SELLER);
        }
        Store store = findStore(storeId);
        store.setName(name);
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }

    @Transactional
    public CommonResponseDto updateStoreCategory(String category, Long storeId, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );
        if(!user.getRole().equals(SELLER)){
            throw new CustomException(ExceptionCode.FORBIDDEN_YOUR_NOT_SELLER);
        }
        Store store = findStore(storeId);
        store.setCategory(category);
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }

    @Transactional
    public CommonResponseDto updateStoreAddress(String address, Long storeId, Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );
        if(!user.getRole().equals(SELLER)){
            throw new CustomException(ExceptionCode.FORBIDDEN_YOUR_NOT_SELLER);
        }
        Store store = findStore(storeId);
        store.setAddress(address);
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }


    @Transactional
    public CommonResponseDto updateStorePhone(String phone, Long storeId, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );
        if(!user.getRole().equals(SELLER)){
            throw new CustomException(ExceptionCode.FORBIDDEN_YOUR_NOT_SELLER);
        }
        Store store = findStore(storeId);
        store.setPhone(phone);
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }


    @Transactional
    public CommonResponseDto updateStoreContent(String content, Long storeId, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );
        if(!user.getRole().equals(SELLER)){
            throw new CustomException(ExceptionCode.FORBIDDEN_YOUR_NOT_SELLER);
        }
        Store store = findStore(storeId);
        store.setContent(content);
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }

    @Transactional
    public CommonResponseDto updateStoreMinDeliveryPrice(Integer minDeliveryPrice, Long storeId, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );
        if(!user.getRole().equals(SELLER)){
            throw new CustomException(ExceptionCode.FORBIDDEN_YOUR_NOT_SELLER);
        }
        Store store = findStore(storeId);
        store.setMinDeliveryPrice(minDeliveryPrice);
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }

    @Transactional
    public CommonResponseDto updateStoreOpenStatus(Boolean openStatus, Long storeId, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );
        if(!user.getRole().equals(SELLER)){
            throw new CustomException(ExceptionCode.FORBIDDEN_YOUR_NOT_SELLER);
        }
        Store store = findStore(storeId);
        store.setOpenStatus(openStatus);
        return new CommonResponseDto(CommonResponseCode.STORE_UPDATE);
    }

    // User 든 Seller든 모두 가능
    public List<StoreResponseDto> getTotalStoreList(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );
        return storeRepository.findAllByIsDeletedFalse().stream().map(this::convertStoreResponseDto).toList();
    }
    public List<StoreResponseDto> getStoreList(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );
        if(!user.getRole().equals(SELLER)){
            throw new CustomException(ExceptionCode.FORBIDDEN_YOUR_NOT_SELLER);
        }
        return storeRepository.findAllByUserIdAndIsDeletedFalse(id).stream().map(this::convertStoreResponseDto).toList();
    }

    public StoreResponseDto getStore(Long id, Long storeId) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_USER)
        );
        Store store = findStore(storeId);
        if(store.isDeleted()){
            throw new CustomException(ExceptionCode.NOT_FOUND_STORE);
        }
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