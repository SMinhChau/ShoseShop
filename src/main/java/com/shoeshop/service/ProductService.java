package com.shoeshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shoeshop.dto.ProductDto;
import com.shoeshop.entity.Product;
import com.shoeshop.entity.ProductSize;

@Service
public interface ProductService {

	Product getProductById(long id);

	List<Product> getRelativedProducts(Long productLineId);

	List<Product> getProductBestSell();

	Page<Product> getProductsByProductLineId(Long id, Pageable pageable);

	Boolean addProduct(Product product);

	Product findLastProduct();

	Page<Product> getProductsNew(Pageable pageable, String textSearch);

	List<ProductDto> getListProductDto(Pageable pageable);

	List<ProductSize> getProductSizesStillSaleForProduct(long productId);

    void updateProduct(Product product);

    Page<Product> findProductsByKeyInName(String key, Pageable pageable);
}
