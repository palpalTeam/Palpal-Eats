package com.sparta.palpaleats.domain.review.entity.service;

import com.sparta.palpaleats.domain.order.entity.Order;
import com.sparta.palpaleats.domain.order.repository.OrderRepository;
import com.sparta.palpaleats.domain.review.entity.Review;
import com.sparta.palpaleats.domain.review.entity.dto.ReviewRequestDto;
import com.sparta.palpaleats.domain.review.entity.dto.ReviewResponseDto;
import com.sparta.palpaleats.domain.review.entity.repository.ReviewRepository;
import com.sparta.palpaleats.domain.store.entity.Store;
import com.sparta.palpaleats.domain.store.repository.StoreRepository;
import com.sparta.palpaleats.domain.user.entity.User;
import com.sparta.palpaleats.domain.user.repository.UserRepository;
import com.sparta.palpaleats.global.exception.CustomException;
import com.sparta.palpaleats.global.exception.ExceptionCode;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;

    public void creatReview(ReviewRequestDto requestDto, Long orderId, Long userId) {

        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_ORDER)
        );

        if (order.isDeleted()) {
            throw new CustomException(ExceptionCode.BAD_REQUEST_NOT_MATCH_ORDER);
        }

        if(!order.getUser().getId().equals(userId)) {
            throw new CustomException(ExceptionCode.FORBIDDEN_YOUR_NOT_COME_IN);
        }

        Store store = storeRepository.findById(order.getStore().getId()).orElseThrow(
                () -> new CustomException(ExceptionCode.NOT_FOUND_STORE)
        );

        if (store.isDeleted()) {
            throw new CustomException(ExceptionCode.BAD_REQUEST_NOT_MATCH_STORE);
        }


        Review review = new Review(requestDto, order, order.getStore());

        reviewRepository.save(review);
    }

    public ReviewResponseDto getStoreReviewDto(Long storeId, Long reviewId) {

        if(!storeRepository.existsById(storeId)) {
            throw new CustomException(ExceptionCode.NOT_FOUND_STORE);
        }

        Review review = findAndGetReview(reviewId);

        return new ReviewResponseDto(review);
    }

    public List<ReviewResponseDto> getStoreReviewList(Long storeId) {

        if(!storeRepository.existsById(storeId)) {
            throw new CustomException(ExceptionCode.NOT_FOUND_STORE);
        }

        List<ReviewResponseDto> reviewResponseDtoList = reviewRepository.findAllByStoreId(storeId).stream().map(ReviewResponseDto::new).toList();

        return reviewResponseDtoList;
    }

    public ReviewResponseDto getUserReview(Long userId, Long reviewId) {

        Review review = findAndGetReview(reviewId);

        if(!review.getOrder().getUser().getId().equals(userId)) {
            throw new CustomException(ExceptionCode.NOT_FOUND_USER);
        }

        return new ReviewResponseDto(review);
    }

    public List<ReviewResponseDto> getUserReviewList(Long userId) {

        if(!userRepository.existsById(userId)) {
            throw new CustomException(ExceptionCode.NOT_FOUND_USER);
        }

        List<Order> orderList = orderRepository.findAllByUserId(userId);
        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();
        for(Order order : orderList) {
            if(order.getUser().getId().equals(userId)) {
                reviewResponseDtoList.add(new ReviewResponseDto(order.getReview()));
            }
        }

        return reviewResponseDtoList;
    }

    @Transactional
    public void updateReview(Long reviewId, ReviewRequestDto reviewRequestDto, User user) {

        Review review = getUserReview(reviewId, user);

        review.setContent(reviewRequestDto.getContent());
        review.setRating(reviewRequestDto.getRating());
    }

    public void deleteReview(Long reviewId, User user) {

        Review review = getUserReview(reviewId, user);

        reviewRepository.delete(review);
    }


    private Review getUserReview(Long reviewId, User user) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_REVIEW));

        if (!user.getId().equals(review.getOrder().getUser().getId())) {
            throw new CustomException(ExceptionCode.FORBIDDEN_YOUR_NOT_COME_IN);
        }

        return review;
    }

    public Review findAndGetReview(Long reviewId) {

        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ExceptionCode.NOT_FOUND_USER));
    }

}
