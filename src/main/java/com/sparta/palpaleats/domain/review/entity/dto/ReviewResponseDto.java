package com.sparta.palpaleats.domain.review.entity.dto;

import com.sparta.palpaleats.domain.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDto {
    private String nickname;
    private String contents;
    private Integer rating;
    private String createdAt;
    public ReviewResponseDto(Review review) {
        this.nickname = review.getOrder().getUser().getNickname();
        this.contents = review.getContent();
        this.rating = review.getRating();
        this.createdAt = review.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}