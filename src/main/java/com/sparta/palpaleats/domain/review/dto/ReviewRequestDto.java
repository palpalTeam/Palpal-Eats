package com.sparta.palpaleats.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class ReviewRequestDto {

    @Min(1)
    @Max(5)
    private Integer rating;
    private String content;

}