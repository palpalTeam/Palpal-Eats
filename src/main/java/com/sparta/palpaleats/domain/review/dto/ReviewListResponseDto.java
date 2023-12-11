package com.sparta.palpaleats.domain.review.dto;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ReviewListResponseDto {
    private List<ReviewResponseDto> reviewList;

    public ReviewListResponseDto(List<ReviewResponseDto> reviewList) {
        this.reviewList = reviewList;
    }


}
