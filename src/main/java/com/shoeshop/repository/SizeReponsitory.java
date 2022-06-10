package com.shoeshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoeshop.entity.Size;

@Repository
public interface SizeReponsitory extends JpaRepository<Size,Long> {
    List<Size> findAll();
    Size findSizeById(Long id);
}
