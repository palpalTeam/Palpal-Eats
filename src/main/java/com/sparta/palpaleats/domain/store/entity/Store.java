package com.sparta.palpaleats.domain.store.entity;

import com.sparta.palpaleats.domain.menu.entity.Menu;
import com.sparta.palpaleats.domain.order.entity.Order;
import com.sparta.palpaleats.domain.review.entity.Review;
import com.sparta.palpaleats.domain.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Table(name = "stores")
@EntityListeners(AuditingEntityListener.class)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 20)
    private String category;

    @Column(nullable = false)
    private String address;

    @Column(length = 1000)
    private String storePictureUrl;

    @Column
    private String storePicturePath;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column
    private String content;

    @Column(nullable = false)
    private Integer minDeliveryPrice;

    @Column(nullable = false)
    private boolean isDeleted;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "store")
    private List<Order> orederList = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Menu> menuList = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Review> reviewList = new ArrayList<>();

    public void updatePicture(String[] urlArr) {
        this.storePictureUrl = urlArr[0];
        this.storePicturePath = urlArr[1];
    }

    public Double getAverageReviewRating(){
        int sum = 0;
        if(this.reviewList.size() == 0)
            return (double) 0;
        for(Review review : this.reviewList){
            sum += review.getRating();
        }
        return (double) (sum / reviewList.size());
    }

    public String getReviewCount(){
        if(this.reviewList.size() > 100){
            return "100+";
        }
        return String.valueOf(this.reviewList.size());
    }

    public void addMenuList(Menu menu) {
        this.menuList.add(menu);
        menu.setStore(this);
    }
}
