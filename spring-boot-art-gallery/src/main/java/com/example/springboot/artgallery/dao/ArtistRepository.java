package com.example.springboot.artgallery.dao;

import com.example.springboot.artgallery.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "artists")
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    Optional<Artist> findByEmail(String email);

}
