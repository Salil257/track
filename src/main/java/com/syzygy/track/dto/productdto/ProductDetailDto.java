package com.syzygy.track.dto.productdto;

import com.syzygy.track.entity.Comment;
import lombok.Data;

import java.util.List;

@Data
public class ProductDetailDto {
   private String id;
   private String location;
   private String Date;
   private List<Comment> comments;
}
