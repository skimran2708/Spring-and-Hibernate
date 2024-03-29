package com.example.springboot.artgallery.service;

import com.example.springboot.artgallery.dao.ArtistRepository;
import com.example.springboot.artgallery.entity.Artist;
import com.example.springboot.artgallery.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService{
    private ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository theArtistRepository) {
        artistRepository=theArtistRepository;
    }

    @Override
    public List<Artist> findAllArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findArtistById(int theId) {
        Optional<Artist> result = artistRepository.findById(theId);

        Artist theArtist = null;

        if (result.isPresent()) {
            theArtist = result.get();
        }
        else {
            // we didn't find the artist
            throw new MyException("Did not find Artist id - " + theId);
        }

        return theArtist;
    }

    @Override
    public Artist findArtistByEmail(String username) {
        Optional<Artist> result = artistRepository.findByEmail(username);

        Artist theArtist = null;

        if (result.isPresent()) {
            theArtist = result.get();
        }
        else {
            // we didn't find the artist
            throw new MyException("Did not find Artist - " + username);
        }

        return theArtist;
    }

    @Override
    public void saveArtist(Artist theArtist) {
        artistRepository.save(theArtist);
    }

    @Override
    public void deleteArtistById(int theId) {
        artistRepository.deleteById(theId);

    }

}


