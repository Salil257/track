package com.syzygy.track.dto.userdto;

import lombok.Data;

import java.util.Date;
@Data
public class UserProductDto {
    private String productId;
    private String name;
    private Date registerDate;
}
