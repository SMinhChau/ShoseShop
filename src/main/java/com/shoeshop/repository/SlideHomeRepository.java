package com.shoeshop.repository;

import com.shoeshop.entity.SlideHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideHomeRepository extends JpaRepository<SlideHome,Long> {
    @Override
    List<SlideHome> findAll();
}
