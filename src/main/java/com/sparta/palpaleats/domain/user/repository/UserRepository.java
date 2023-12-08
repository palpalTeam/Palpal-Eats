package com.sparta.palpaleats.domain.user.repository;

import com.sparta.palpaleats.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailOrNickname(String email, String nickname);


    Optional<User> findByEmail(String email);

    boolean existsByNickname(String nickname);
}
