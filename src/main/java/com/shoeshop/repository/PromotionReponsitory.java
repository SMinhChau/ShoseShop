package com.shoeshop.repository;

import com.shoeshop.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionReponsitory extends JpaRepository<Promotion, Long> {
    Promotion findPromotionByCode(String code);

    Promotion findPromotionById(long id);

}
