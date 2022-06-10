package com.shoeshop.service.serviceImpl;

import com.shoeshop.dto.BrandDto;
import com.shoeshop.dto.ProductLineDto;
import com.shoeshop.entity.Brand;
import com.shoeshop.entity.ProductLine;
import com.shoeshop.repository.BrandRepository;
import com.shoeshop.repository.ProductRepository;
import com.shoeshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<BrandDto> getAllBrandAndListProduct() {
        List<Brand> brands =   brandRepository.findAll();
        List<BrandDto> brandDtos = new ArrayList<>();
        brands.forEach(item->{
            BrandDto brandDto = new BrandDto();
            brandDto.setId(item.getId());
            brandDto.setName(item.getName());
            brandDto.setImage(item.getImage());
            brandDto.setProducts(productRepository.findProductsByBrandRandomLimit(item.getId(),8));
            List<ProductLineDto> productLineDtos = new ArrayList<>();
            item.getProductLines().forEach(item1->{
                ProductLineDto productLineDto = new ProductLineDto();
                productLineDto.setId(item1.getId());
                productLineDto.setName(item1.getName());
                productLineDtos.add(productLineDto);
            });
            brandDto.setProductLines(productLineDtos);
            brandDtos.add(brandDto);
        });

        return  brandDtos;
    }

    private ProductLineDto convertToProductLineDto(ProductLine productLine, Pageable pageable){
        ProductLineDto productLineDto = new ProductLineDto();
        productLineDto.setId(productLine.getId());
        productLineDto.setName(productLine.getName());
        productLineDto.setProductPage(productRepository.findProductsByProductLineId(productLine.getId(), pageable));
        return  productLineDto;
    }

    @Override
    public BrandDto getBrandByIdAndProductPageOfProductLine(Long id,Pageable pageable) {
        Brand brand =   brandRepository.findBrandById(id);
        BrandDto brandDto = new BrandDto();
        brandDto.setId(brand.getId());
        brandDto.setName(brand.getName());
        List<ProductLineDto> productLineDtos = new ArrayList<>();
        brand.getProductLines().forEach(item -> {
                productLineDtos.add(convertToProductLineDto(item,pageable));
        });
        brandDto.setProductLines(productLineDtos);

        return  brandDto;
    }

    @Override
    public Boolean addBrands(Brand brand) {
        if(brandRepository.save(brand )!= null)
            return true;
      return false;
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Page<Brand> getBrandNews(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    @Override
    public Brand findLastBrand() {
        return brandRepository.findTopByOrderByIdDesc();
    }

    @Override
    public Brand findBrandById(long id) {
        Brand brand= brandRepository.findBrandById(id);
        if(brand == null){
            throw  new RuntimeException("Brand is null!");
        }
        return brand;
    }

    @Override
    public Brand updateBrand(Brand brand) {
        return brandRepository.save(brand);
    }

}
