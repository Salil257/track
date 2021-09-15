package com.syzygy.track.service;

import com.syzygy.track.dto.productdto.ProductDto;
import com.syzygy.track.entity.Product;
import com.syzygy.track.mapper.DtoMapper;
import com.syzygy.track.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    @Autowired
    private DtoMapper dtoMapper;
    @Autowired
    private ProductRepository repo;

    public List<ProductDto> findAllProduct() {
        return dtoMapper.productListToDto(repo.findAll());
    }
    public String saveProduct(Product product){
        repo.save(product);
        return"save";
    }

    public ProductDto getProduct(String id){
      return  dtoMapper.productToProductDto(repo.findById(id).get());
    }

}
