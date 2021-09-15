package com.syzygy.track.Controller;

import com.syzygy.track.Dto.ProductDTO.ProductDto;
import com.syzygy.track.Entity.Product;
import com.syzygy.track.Service.ProductService;
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
