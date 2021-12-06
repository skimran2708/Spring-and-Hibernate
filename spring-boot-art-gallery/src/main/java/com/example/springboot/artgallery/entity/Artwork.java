package com.example.springboot.artgallery.entity;


import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name="art_work")
public class Artwork {

    // define our fields

    // define constructors

    // define getter setters

    // define tostring

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

    public Artwork() {

    }

    public Artwork(String title, String year) {
        this.title = title;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return "Artwork{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
