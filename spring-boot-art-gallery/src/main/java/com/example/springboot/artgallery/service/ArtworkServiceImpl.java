package com.example.springboot.artgallery.service;

import com.example.springboot.artgallery.dao.ArtworkRepository;
import com.example.springboot.artgallery.entity.Artwork;
import com.example.springboot.artgallery.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtworkServiceImpl implements ArtworkService {
    private ArtworkRepository artworkRepository;

    @Autowired
    public ArtworkServiceImpl(ArtworkRepository theArtworkRepository) {
        artworkRepository= theArtworkRepository;
    }

    @Override
    public Artwork findArtworkById(int theId) {
        Optional<Artwork> result = artworkRepository.findById(theId);

        Artwork theArtwork = null;

        if (result.isPresent()) {
            theArtwork = result.get();
        }
        else {
            // we didn't find the artwork
            throw new MyException("Did not find Artwork id - " + theId);
        }

        return theArtwork;
    }

    @Override
    public void saveArtwork(Artwork theArtwork) {
        artworkRepository.save(theArtwork);
    }

    @Override
    public void deleteArtworkById(int theId) {
        artworkRepository.deleteById(theId);
    }
}
