package com.sparta.palpaleats.domain.review.repository;


import com.sparta.palpaleats.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByStoreId(Long storeId);

}
