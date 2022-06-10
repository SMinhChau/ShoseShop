package com.shoeshop.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoeshop.dto.ProductDto;
import com.shoeshop.entity.Product;
import com.shoeshop.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductRest {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product-line")
    public Page<Product> getPageProductByProductLine(@RequestParam("productLineId") Long productLineId,
            @RequestParam("pageIndex") int pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex - 1, 8);
        Page<Product> productPage = productService.getProductsByProductLineId(productLineId, pageable);

        return productPage;
    }

    @GetMapping(params = "page")
    public List<ProductDto> getListProducts(@RequestParam(required = false, name = "page") Integer goToPage,
            @RequestParam(required = false, name = "rowOfPage") Integer rowOfPage,
            @RequestParam(required = false, name = "sort", defaultValue = "id") String sort,
            @RequestParam(required = false, name = "orderby", defaultValue = "desc") String orderBy) {
        if (!(rowOfPage == 10 || rowOfPage == 25 || rowOfPage == 50 || rowOfPage == 100))
            rowOfPage = 10;
        if (!(sort.equalsIgnoreCase("id") || sort.equalsIgnoreCase("name") || sort.equalsIgnoreCase("productLine")
                || sort.equalsIgnoreCase("price") || sort.equalsIgnoreCase("promotionPrice")
                || sort.equalsIgnoreCase("category")))
            sort = "name";

        Direction orderBySort = Sort.Direction.DESC;
        if (orderBy.equalsIgnoreCase("asc"))
            orderBySort = Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(goToPage - 1, rowOfPage, Sort.by(orderBySort, sort));
        List<ProductDto> pageList = productService.getListProductDto(pageable);
        return pageList;
    }

}
