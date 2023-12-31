package com.sparta.palpaleats.domain.review.entity;

import com.sparta.palpaleats.domain.order.entity.Order;
import com.sparta.palpaleats.domain.review.dto.ReviewRequestDto;
import com.sparta.palpaleats.domain.store.entity.Store;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Setter
@Getter
@Table(name = "reviews")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private Integer rating;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public Review(ReviewRequestDto requestDto, Order order, Store store) {
        this.content = requestDto.getContent();
        this.rating = requestDto.getRating();
        this.order = order;
        this.store = store;
    }
}
