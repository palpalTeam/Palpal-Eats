package com.sparta.palpaleats.domain.review.entity.repository;


import com.sparta.palpaleats.domain.review.entity.Review;
import com.sparta.palpaleats.domain.review.entity.dto.ReviewResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByStoreId(Long storeId);

    List<Review> findAllByUserId(Long userId);
}
