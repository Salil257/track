package com.syzygy.track.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "User_data")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String Address;
    @Column(name = "pincode")
    private Long pincode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "user_id", referencedColumnName = "id")
    private User user;
}
