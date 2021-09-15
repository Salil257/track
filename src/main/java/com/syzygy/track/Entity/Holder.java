package com.syzygy.track.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "Holder")
@Data
public class Holder {
    @Id

    private Long id;
    private String userName;
    private String phoneNo;
    private String location;
    private String role;
    private List<Comment> comments;
}
