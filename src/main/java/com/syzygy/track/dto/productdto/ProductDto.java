package com.syzygy.track.dto.productdto;
import com.syzygy.track.entity.Holder;
import com.syzygy.track.utils.PackagingType;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class ProductDto {
    private String id;
    private String name;
    private String company;
    private String companyId;
    private PackagingType packagingType;
    private String manufacturedBy;
    private Date manufacturingDate;
    private Date expiryDate;
    private String uid;
    private String gtin;
    private List<Holder> holders;
}
