package com.sparta.palpaleats.domain.menu.repository;

import com.sparta.palpaleats.domain.menu.dto.MenuResponseDto;
import com.sparta.palpaleats.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByStoreId(Long storeId);
}