package com.shoeshop.service;

import com.shoeshop.entity.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionService {
    Promotion getPromotionByCode(String code);

    Promotion findPromotionById(long id);

    List<Promotion> findAll();

    void updatePromotion(Promotion promotion);

    Promotion addPromotion(Promotion promotion);
}
