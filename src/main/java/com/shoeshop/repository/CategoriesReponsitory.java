package com.shoeshop.repository;

import com.shoeshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesReponsitory extends JpaRepository<Category, Long> {
    Category findCategoryById(Long id);

    Category findTopByOrderByIdDesc();

    List<Category> findAll();

}
