package com.sparta.palpaleats.domain.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreRequestDto {
    private String name;
    private String category;
    private String address;
    private Integer minDeliveryPrice;
    private String storePictureUrl;
    private String phone;
    private String content;
    private boolean openStatus;
}
