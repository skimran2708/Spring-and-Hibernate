package com.example.springboot.artgallery.service;

import com.example.springboot.artgallery.entity.Artwork;

public interface ArtworkService {

    public Artwork findById(int theId);

    public void save(Artwork theArtwork);

    public void deleteById(int theId);
}
