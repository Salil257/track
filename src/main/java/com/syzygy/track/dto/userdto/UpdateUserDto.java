package com.syzygy.track.dto.userdto;

import lombok.Data;

@Data
public class UpdateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String Address;
    private Long pincode;
}
