package com.sparta.palpaleats.domain.menu.repository;

import com.sparta.palpaleats.domain.menu.dto.MenuResponseDto;
import com.sparta.palpaleats.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    
    List<Menu> findAllByStoreIdAndIsDeletedFalse(Long storeId);

    Optional<Menu> findByIdAndIsDeleteFalse(Long menuId);
}