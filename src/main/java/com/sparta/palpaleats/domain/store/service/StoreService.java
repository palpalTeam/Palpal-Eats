package com.sparta.palpaleats.domain.store.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sparta.palpaleats.domain.store.dto.CommonResponseCode;
import com.sparta.palpaleats.domain.store.dto.CommonResponseDto;
import com.sparta.palpaleats.domain.store.dto.StoreRequestDto;
import com.sparta.palpaleats.domain.store.entity.Store;
import com.sparta.palpaleats.domain.store.repository.StoreRepository;
import com.sparta.palpaleats.global.exception.CustomException;
import com.sparta.palpaleats.global.exception.ExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final AmazonS3 amazonS3;
    private final StoreRepository storeRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    public CommonResponseDto addStore(StoreRequestDto storeRequestDto) {
        Store store = new Store();
        store.setName(storeRequestDto.getName());
        store.setCategory(storeRequestDto.getCategory());
        store.setAddress(storeRequestDto.getAddress());
        store.setContent(storeRequestDto.getContent());
        store.setMinDeliveryPrice(storeRequestDto.getMinDeliveryPrice());
        store.setPhone(storeRequestDto.getPhone());
        store.setOpenStatus(store.isOpenStatus());
        storeRepository.save(store);
        return new CommonResponseDto(CommonResponseCode.STORE_CREATE);
    }

    @Transactional
    public CommonResponseDto addStorePicture(MultipartFile multipartFile, Long id) throws IOException {
        Store store = findStore(id);
        if (!multipartFile.isEmpty()) {
            store.updatePicture(saveFile(multipartFile));
        }
        return new CommonResponseDto(CommonResponseCode.STORE_SET_IMAGE);
    }

    private Store findStore(Long id) {
        return storeRepository.findById(id).orElseThrow(() ->
                new CustomException(ExceptionCode.NOT_FOUND_STORE));
    }

    private String saveFile(MultipartFile multipartFile){
        validateFileExists(multipartFile);
        String fileName = multipartFile.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    private void validateFileExists(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
