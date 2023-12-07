package com.sparta.palpaleats.domain.cart.entity;

import com.sparta.palpaleats.domain.menu.entity.Menu;
import com.sparta.palpaleats.domain.order.entity.Order;
import com.sparta.palpaleats.domain.store.entity.Store;
import com.sparta.palpaleats.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Integer quantity;

    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    Menu menu;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    Store store;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    Order order;
}
