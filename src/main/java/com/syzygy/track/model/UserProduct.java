package com.syzygy.track.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Product_list")
public class UserProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "name")
    private String name;
    @Column(name = "register_date")
    private Date registerDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "pk_fk", referencedColumnName = "id")
    private User user;
}
