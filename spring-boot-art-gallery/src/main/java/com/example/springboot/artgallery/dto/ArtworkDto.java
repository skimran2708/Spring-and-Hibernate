package com.example.springboot.artgallery.dto;

import lombok.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtworkDto {

    private int id;

    @Size(min = 2, message = "Title should be atleast 2 characters")
    private String title;

    @Positive(message = "Year should be greater than zero")
    @Size(min = 3, max = 4, message = "Invalid year")
    private String year;
}
