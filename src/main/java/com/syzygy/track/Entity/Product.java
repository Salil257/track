package com.syzygy.track.Entity;

import com.syzygy.track.utils.PackagingType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Product")
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String company;
    private String companyId;
    private PackagingType packagingType;
    private String manufacturedBy;
    private Date registerDate;
    private Date expiryDate;
    private String uid;
    private String gtin;
    private List<Holder> holders;
    private List<Product> products;
}
