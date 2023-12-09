package com.sparta.palpaleats.domain.review.entity.controller;

import com.sparta.palpaleats.domain.review.entity.dto.ReviewListResponseDto;
import com.sparta.palpaleats.domain.review.entity.dto.ReviewRequestDto;
import com.sparta.palpaleats.domain.review.entity.dto.ReviewResponseDto;
import com.sparta.palpaleats.domain.review.entity.service.ReviewService;
import com.sparta.palpaleats.global.dto.CommonResponseDto;
import com.sparta.palpaleats.global.security.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@Slf4j
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/myinfo/orders/{orderId}/review") // 리뷰 작성
    public ResponseEntity<CommonResponseDto> postReview(
            @RequestBody ReviewRequestDto reviewRequestDto, @PathVariable Long orderId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        reviewService.creatReview(reviewRequestDto, orderId, userDetails.getUser().getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponseDto(HttpStatus.CREATED.value(), "리뷰가 작성되었습니다"));
    }

    @GetMapping("/stores/{storeId}/reviews/{reviewId}") // 가게 리뷰 단일 조회
    public ResponseEntity<ReviewResponseDto> getStoreReview(@PathVariable Long storeId,
            @PathVariable Long reviewId) {

        ReviewResponseDto reviewResponseDto = reviewService.getStoreReviewDto(storeId, reviewId);

        return ResponseEntity.ok().body(reviewResponseDto);

    }

    @GetMapping("/stores/{storeId}/reviews") // 가게 리뷰 목록 조회
    public ResponseEntity<ReviewListResponseDto> getStoreReviewList(@PathVariable Long storeId) {

        ReviewListResponseDto reviewListResponseDto = new ReviewListResponseDto(
                reviewService.getStoreReviewList(storeId));

        return ResponseEntity.ok().body(reviewListResponseDto);
    }

    @GetMapping("/users/{userId}/reviews/{reviewId}") // 유저 리뷰 단일 조회
    public ResponseEntity<ReviewResponseDto> getUserReview(@PathVariable Long userId,
            @PathVariable Long reviewId) {

        ReviewResponseDto reviewResponseDto = reviewService.getUserReview(userId, reviewId);

        return ResponseEntity.status(HttpStatus.OK).body(reviewResponseDto);
    }

    @GetMapping("/users/{userId}/reviews/") // 유저 리뷰 전체 조회
    public ResponseEntity<ReviewListResponseDto> getUserReviewList(@PathVariable Long userId) {

        ReviewListResponseDto reviewListResponseDto = new ReviewListResponseDto(
                reviewService.getUserReviewList(userId));

        return ResponseEntity.ok().body(reviewListResponseDto);
    }

    @PutMapping("/reviews/{reviewId}") // 리뷰 수정
    public ResponseEntity<CommonResponseDto> putComment(@PathVariable Long commentId, // TODO 이 부분 commentId로 받으시면 PathVariable이 인식을 못해용
            @RequestBody ReviewRequestDto reviewRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.updateReview(commentId, reviewRequestDto, userDetails.getUser());
        return ResponseEntity.ok()
                .body(new CommonResponseDto(HttpStatus.BAD_REQUEST.value(), "리뷰가 수정되었습니다."));
    }

    @DeleteMapping("/reviews/{reviewId}") // 리뷰 삭제
    public ResponseEntity<CommonResponseDto> deleteReview(@PathVariable Long reviewId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            reviewService.deleteReview(reviewId, userDetails.getUser());
            return ResponseEntity.status(HttpStatus.CREATED.value())
                    .body(new CommonResponseDto(HttpStatus.CREATED.value(), "리뷰가 삭제되었습니다."));
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest()
                    .body(new CommonResponseDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }


}
