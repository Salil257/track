package com.syzygy.track.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Comment")
public class Comment {
    private String id;
    private String text;
    private String time;

}
