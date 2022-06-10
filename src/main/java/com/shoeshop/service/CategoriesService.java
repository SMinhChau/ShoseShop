package com.shoeshop.service;

import com.shoeshop.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoriesService {
    Category findCategoryById(long id);

    Category addCategories(Category category);

    Category findLastCategories();

    List<Category> findAll();

    Category updateCategory(Category category);





}
