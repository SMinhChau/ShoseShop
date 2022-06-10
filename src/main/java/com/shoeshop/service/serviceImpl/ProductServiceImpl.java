package com.shoeshop.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.shoeshop.entity.ProductSize;
import com.shoeshop.repository.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shoeshop.dto.ProductDto;
import com.shoeshop.dto.ProductLineDto;
import com.shoeshop.entity.Product;
import com.shoeshop.entity.ProductLine;
import com.shoeshop.entity.Tag;
import com.shoeshop.repository.ProductRepository;
import com.shoeshop.repository.TagRepository;
import com.shoeshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository producReponsitory;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private ProductSizeRepository productSizeRepository;

	@Override
	public Product getProductById(long id) {
		Product product = producReponsitory.findProductById(id);
		return product;
	}

	private ProductLineDto convertToProductLineDto(ProductLine productLine, Pageable pageable) {
		ProductLineDto productLineDto = new ProductLineDto();
		productLineDto.setId(productLine.getId());
		productLineDto.setName(productLine.getName());
		productLineDto.setProductPage(producReponsitory.findProductsByProductLineId(productLine.getId(), pageable));
		return productLineDto;
	}

	@Override
	public List<ProductDto> getListProductDto(Pageable pageable) {
		Page<Product> page = producReponsitory.findAll(pageable);
		List<ProductDto> list = new ArrayList<ProductDto>();
		page.forEach(e -> {
			ProductDto productDto = new ProductDto();
			productDto.setId(e.getId());
			productDto.setAvatar(e.getAvatar());
			productDto.setCategory(e.getCategory());
			productDto.setName(e.getName());
			productDto.setPrice(e.getPrice());
			productDto.setPromotionPrice(e.getPromotionPrice());
			productDto.setProductLineDto(convertToProductLineDto(e.getProductLine(), pageable));
			list.add(productDto);
		});

		return list;
	};

	@Override
	public List<Product> getRelativedProducts(Long productLineId) {
		return producReponsitory.findTop20ByProductLineIdOrderByName(productLineId);
	}

	@Override
	public List<Product> getProductBestSell() {
		Tag tag = tagRepository.getFirstByName("best sell");
		return tag.getProducts();
	}

	@Override
	public Page<Product> getProductsByProductLineId(Long id, Pageable pageable) {
		return producReponsitory.findProductsByProductLineId(id, pageable);
	};

	@Override
	public Boolean addProduct(Product product) {
		return producReponsitory.save(product) != null;
	}

	@Override
	public Product findLastProduct() {
		return producReponsitory.findTopByOrderByIdDesc();
	}

	@Override
	public Page<Product> getProductsNew(Pageable pageable, String text) {
		if(text.equals("")) {
			return producReponsitory.findAll(pageable);
		}
		return producReponsitory.findProductsByNameContainingIgnoreCase(text, pageable);
	}

	@Override
	public List<ProductSize> getProductSizesStillSaleForProduct(long productId){

		return productSizeRepository.findProductSizesByProductIdAndStatusAndQuantityGreaterThan(productId, true, 0);
	}

	@Override
	public void updateProduct(Product product){
		producReponsitory.save(product);
	}

	@Override
	public Page<Product> findProductsByKeyInName(String key, Pageable pageable){
		return producReponsitory.findProductsByNameContainingIgnoreCaseCustom(key,pageable);
	}

}
