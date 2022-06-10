package com.shoeshop.service.serviceImpl;


import com.shoeshop.entity.ProductLine;
import com.shoeshop.repository.ProductLineRepository;
import com.shoeshop.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductLineServiceImpl implements ProductLineService {
    @Autowired
    private ProductLineRepository productLineRepository;

    @Override
    public ProductLine getProductLine(Long id){
        return productLineRepository.findProductLineById(id);
    }
    
    @Override
	public  List<ProductLine> getProductLinesByBrandId(long id) {
    	return productLineRepository.findProductLinesByBrandId(id);
    }

    @Override
    public ProductLine addProductLine(ProductLine productLine) {
        return productLineRepository.save(productLine);
    }

    @Override
    public List<ProductLine> findAll() {
        return productLineRepository.findAll();
    }

    @Override
    public ProductLine updateProductLine(ProductLine productLine1) {
        return productLineRepository.save(productLine1);
    }
}
