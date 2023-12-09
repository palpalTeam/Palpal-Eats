package com.sparta.palpaleats.domain.store.repository;

import com.sparta.palpaleats.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {

    List<Store> findAllByIsDeletedFalse();

    List<Store> findAllByUserIdAndIsDeletedFalse(Long id);

    Optional<Store> findByIdAndIsDeleteFalse(Long id);
}