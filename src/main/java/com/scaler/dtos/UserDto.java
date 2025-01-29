package com.scaler.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDto {
    private String name;
    private String email;
    private List<Role> roles;

    public UserDto() {

    }

    public UserDto(String name, String email ) {
        this.name = name;
        this.email = email;
    }
}
