package com.sparta.palpaleats.domain.menu.entity;

import com.sparta.palpaleats.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String category;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Integer price;

    @Column
    String menuPictureUrl;

    @Column
    String menuPicturePath;

    @Column(nullable = false)
    private boolean isDeleted;

    @Column(nullable = false)
    @CreatedDate
    LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    public void updatePicture(String[] urlArr) {
        this.menuPictureUrl = urlArr[0];
        this.menuPicturePath = urlArr[1];
    }
}