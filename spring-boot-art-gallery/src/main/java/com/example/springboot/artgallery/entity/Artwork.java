package com.example.springboot.artgallery.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name="art_work")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Artwork {

    // define our fields

    // define constructors

    // define getter setters

    // define toString

    // annotate fields

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Size(min = 2, message = "Title should be atleast 2 characters")
    @Column(name="title")
    private String title;

    @Positive(message = "Year should be greater than zero")
    @Size(min = 3, max = 4, message = "Invalid year")
    @Column(name="year")
    private String year;


    public Artwork(int id, String title, String year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public Artwork(String title, String year) {
        this.title = title;
        this.year = year;
    }

}
