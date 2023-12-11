package com.sparta.palpaleats.domain.store.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreResponseDto {
    private String name;
    private String category;
    private String content;
    private String storePictureUrl;
    private Double reviewRatingAvg;
    private String reviewCount;
    private Boolean openStatus;

}