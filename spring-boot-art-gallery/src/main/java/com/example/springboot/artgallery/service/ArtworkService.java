package com.example.springboot.artgallery.service;

import com.example.springboot.artgallery.entity.Artwork;

public interface ArtworkService {

    public Artwork findArtworkById(int theId);

    public void saveArtwork(Artwork theArtwork);

    public void deleteArtworkById(int theId);
}
