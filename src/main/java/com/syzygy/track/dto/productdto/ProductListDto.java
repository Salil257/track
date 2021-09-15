package com.syzygy.track.dto.productdto;


import com.syzygy.track.utils.PackagingType;
import lombok.Data;

@Data
public class ProductListDto {
    private String name;
    private String company;
    private PackagingType packagingType;
}
