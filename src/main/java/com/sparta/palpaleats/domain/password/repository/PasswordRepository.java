package com.sparta.palpaleats.domain.password.repository;

import com.sparta.palpaleats.domain.password.entity.Password;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

    List<Password> findAllByUserIdOrderByCreatedAt(Long id);
}
