package com.shoeshop.service.serviceImpl;

import com.shoeshop.entity.Category;
import com.shoeshop.repository.CategoriesReponsitory;
import com.shoeshop.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesReponsitory categoriesReponsitory;

    @Override
    public Category findCategoryById(long id) {
        Category category= categoriesReponsitory.findCategoryById(id);
        if(category == null){
            throw  new RuntimeException("Category is null!");
        }
        return category;
    }

    @Override
    public Category addCategories(Category category) {
        return categoriesReponsitory.save(category);
    }

    @Override
    public Category findLastCategories() {
        return categoriesReponsitory.findTopByOrderByIdDesc();
    }


    @Override
    public List<Category> findAll() {
        return categoriesReponsitory.findAll();
    }

    @Override
    public Category updateCategory(Category category) {
        return categoriesReponsitory.save(category);
    }

    public Optional<Category> getOne(Long id) {
        return categoriesReponsitory.findById(id);
    }
}
