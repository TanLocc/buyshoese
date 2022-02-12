package com.example.buyshoes.repository;

import com.example.buyshoes.entities.Category;
import com.example.buyshoes.entities.ShoesProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    @Query(value = "SELECT Name FROM Category", nativeQuery = true )
    List<String>  getCategoryNames();

    @Query(value = "Select id from Category where name = :name", nativeQuery = true)
    String getCategoryIdByName(@Param("name") String name);
}
