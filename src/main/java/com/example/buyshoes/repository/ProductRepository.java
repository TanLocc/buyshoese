package com.example.buyshoes.repository;

import com.example.buyshoes.entities.ShoesProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ShoesProduct, Long> {

//    @Query(value = "select * from ShoesProduct where category_id = :category_id", nativeQuery = true)
    Page<ShoesProduct> findAllByCategoryId(Long categoryId, Pageable pageable);
}
