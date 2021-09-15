package com.syzygy.track.Service;

import com.syzygy.track.Dto.ProductDTO.ProductDto;
import com.syzygy.track.Entity.Product;
import com.syzygy.track.Mapper.DtoMapper;
import com.syzygy.track.Repository.ProductRepository;
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
