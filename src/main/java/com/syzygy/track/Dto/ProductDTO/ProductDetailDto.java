package com.syzygy.track.Dto.ProductDTO;

import com.syzygy.track.Entity.Comment;
import lombok.Data;

import java.util.List;

@Data
public class ProductDetailDto {
   private String id;
   private String location;
   private String Date;
   private List<Comment> comments;
}
