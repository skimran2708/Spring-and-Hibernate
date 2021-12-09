package com.example.springboot.artgallery.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    @NotEmpty(message = "Should not be empty")
    private String username;

    @NotEmpty(message = "Should not be empty")
    @Size(min=5 , message = "Password should be minimum 5 characters")
    private String password;

    private short enabled;
}
