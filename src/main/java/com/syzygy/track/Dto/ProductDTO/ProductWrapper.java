package com.syzygy.track.Dto.ProductDTO;

import com.syzygy.track.Dto.ProductDTO.ProductDto;
import com.syzygy.track.Dto.ProductDTO.ProductListDto;
import lombok.Data;

@Data
public class ProductWrapper {
    private ProductDto productDto;
    private ProductListDto productListDto;
}
