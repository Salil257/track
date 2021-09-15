package com.syzygy.track.Dto.UserDTO;

import com.syzygy.track.model.User;
import lombok.Data;

@Data
public class UpdateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String Address;
    private Long pincode;
}
