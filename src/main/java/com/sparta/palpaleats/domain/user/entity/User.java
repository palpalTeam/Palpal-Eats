package com.sparta.palpaleats.domain.user.entity;

import com.sparta.palpaleats.domain.cart.entity.Cart;
import com.sparta.palpaleats.domain.order.entity.Order;
import com.sparta.palpaleats.domain.password.entity.Password;
import com.sparta.palpaleats.domain.store.entity.Store;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Currency;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20, unique = true)
    private String nickname;

    @Column(length = 50)
    private String currentAddress;

    @Column(nullable = false, length = 100)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "user")
    private List<Password> passwordList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Store> storeList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Cart> cartList = new ArrayList<>();

    public User(String email, String password, String currentAddress, String nickname, UserRoleEnum role) {

        this.email = email;
        this.password = password;
        this.currentAddress = currentAddress;
        this.nickname = nickname;
        this.role = role;
    }

    public void addStoreList(Store store) {
        this.storeList.add(store);
        store.setUser(this);
    }
}
