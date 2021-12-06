package com.example.springboot.artgallery.service;

import com.example.springboot.artgallery.entity.Artist;
import com.example.springboot.artgallery.entity.Users;

import java.util.List;

public interface ArtistService {

    public List<Artist> findAll();

    public Artist findById(int theId);

    public Artist findByEmail(String theUsername);

    public void save(Artist theArtist);

    public void deleteById(int theId);
}
