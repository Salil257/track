package com.syzygy.track.controller;

import com.syzygy.track.dto.productdto.ProductDto;
import com.syzygy.track.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProduct")
    public List<ProductDto> findAllProduct() {
        return productService.findAllProduct();
    }

    @GetMapping("/getProduct/{id}")
    public ProductDto findProductById(@PathVariable(value = "id") String id) {
        return productService.getProduct(id);
    }


}
