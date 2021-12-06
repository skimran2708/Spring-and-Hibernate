package com.example.springboot.artgallery.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="artist")
public class Artist {

    // annotate the class as an entity and map to db table

    // define the fields

    // annotate the fields with db column names

    // ** set up mapping to InstructorDetail entity

    // create constructors

    // generate getter/setter methods

    // generate toString() method

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    @Size(min = 2,message = "First Name should be atleast 2 characters")
    private String firstName;

    @Size(min = 2,message = "Last Name should be atleast 2 characters")
    @Column(name="last_name")
    private String lastName;

    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email")
    @Column(name="email", unique = true)
    private String email;

    @Range(min = 1, max = 100, message = "Age should be between [1-100]")
    @Column(name="age")
    private String age;

    @Size(min = 2,message = "Birth Place should be atleast 2 characters")
    @Column(name="birth_place")
    private String birthPlace;

    @NotNull(message = "Choose atleast an option")
    @Column(name="art_style")
    private String artStyle;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "artist_id")
    private List<Artwork> artworks;

    public Artist() {

    }

    public Artist(String firstName, String lastName, String email, String age, String birthPlace, String artStyle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.birthPlace = birthPlace;
        this.artStyle = artStyle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getArtStyle() {
        return artStyle;
    }

    public void setArtStyle(String artStyle) {
        this.artStyle = artStyle;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", artStyle='" + artStyle + '\'' +
                '}';
    }

    public List<Artwork> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
    }


    // add convenience method
    public void addArtwork(Artwork theArtwork) {

        if (artworks == null) {
            artworks = new ArrayList<>();
        }

        artworks.add(theArtwork);

    }

}