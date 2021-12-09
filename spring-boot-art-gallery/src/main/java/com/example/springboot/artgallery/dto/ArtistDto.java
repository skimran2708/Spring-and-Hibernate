package com.example.springboot.artgallery.dto;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto {

    private int id;

    @Size(min = 2,message = "First Name should be atleast 2 characters")
    private String firstName;

    @Size(min = 2,message = "Last Name should be atleast 2 characters")
    private String lastName;

    @NotEmpty(message = "Email should not be null")
    private String email;

    @Range(min = 1, max = 100, message = "Age should be between [1-100]")
    private String age;

    @Size(min = 2,message = "Birth Place should be atleast 2 characters")
    private String birthPlace;

    @NotNull(message = "Choose atleast an option")
    private String artStyle;

}
