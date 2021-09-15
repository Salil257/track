package com.syzygy.track.Dto.ProductDTO;


import com.syzygy.track.utils.PackagingType;
import lombok.Data;

@Data
public class ProductListDto {
    private String name;
    private String company;
    private PackagingType packagingType;
}
