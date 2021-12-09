package com.example.springboot.artgallery;

import com.example.springboot.artgallery.converter.ArtistConvertor;
import com.example.springboot.artgallery.converter.ArtworkConverter;
import com.example.springboot.artgallery.converter.UsersConverter;
import com.example.springboot.artgallery.dto.ArtistDto;
import com.example.springboot.artgallery.dto.ArtworkDto;
import com.example.springboot.artgallery.dto.UsersDto;
import com.example.springboot.artgallery.entity.Artist;
import com.example.springboot.artgallery.entity.Artwork;
import com.example.springboot.artgallery.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ConverterTests {

    @Test
    void entityToDto_convertsArtistEntityToArtistDto() {
        Artist artist = new Artist(1,"Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
        ArtistDto artistDto = new ArtistConvertor().entityToDto(artist);
        assertEquals("Jack",artistDto.getFirstName());
    }

    @Test
    void DtoToEntity_convertsArtistDtoToArtistEntity() {
        ArtistDto artistDto = new ArtistDto(1,"Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
        Artist artist = new ArtistConvertor().dtoToEntity(artistDto);
        assertEquals("Jack",artist.getFirstName());
    }

    @Test
    void entityToDto_convertsArtistEntityLisToArtistDtoList() {
        Artist artist1 = new Artist("Kunal", "Sharma", "kunal@artist.com", "39", "USA", "Contemporary");
        Artist artist2 = new Artist("Pavan", "Kumar", "pavan@artist.com", "28", "Italy", "Cubism");
        List<Artist> artistList = new ArrayList<>();
        artistList.add(artist1);
        artistList.add(artist2);
        List<ArtistDto> artistDtoList = new ArtistConvertor().entityToDto(artistList);
        assertEquals(2,artistDtoList.size());
    }

    @Test
    void dtoToEntity_convertsArtistDtoListToArtistEntityList() {
        ArtistDto artistDto1 = new ArtistDto(1,"Kunal", "Sharma", "kunal@artist.com", "39", "USA", "Contemporary");
        ArtistDto artistDto2 = new ArtistDto(2,"Pavan", "Kumar", "pavan@artist.com", "28", "Italy", "Cubism");
        List<ArtistDto> artistDtoList = new ArrayList<>();
        artistDtoList.add(artistDto1);
        artistDtoList.add(artistDto2);
        List<Artist> artistList = new ArtistConvertor().dtoToEntity(artistDtoList);
        assertEquals(2,artistList.size());
    }

    @Test
    void entityToDto_convertsArtworkEntityToArtworkDto() {
        Artwork artwork = new Artwork(1,"Painting","1780");
        ArtworkDto artworkDto = new ArtworkConverter().entityToDto(artwork);
        assertEquals("Painting",artworkDto.getTitle());
    }

    @Test
    void DtoToEntity_convertsArtworkDtoToArtworkEntity() {
        ArtworkDto artworkDto = new ArtworkDto(1,"Painting","1780");
        Artwork artwork = new ArtworkConverter().dtoToEntity(artworkDto);
        assertEquals("Painting",artwork.getTitle());
    }

    @Test
    void entityToDto_convertsArtworkEntityListToArtworkDtoList() {
        Artwork artwork1 = new Artwork(1,"Painting","1780");
        Artwork artwork2 = new Artwork(2,"Sunflower","1880");
        List<Artwork> artworkList = new ArrayList<>();
        artworkList.add(artwork1);
        artworkList.add(artwork2);
        List<ArtworkDto> artworkDtoList = new ArtworkConverter().entityToDto(artworkList);
        assertEquals(2,artworkDtoList.size());
    }

    @Test
    void dtoToEntity_convertsArtworkDtoListToArtworkEntityList() {
        ArtworkDto artworkDto1 = new ArtworkDto(1,"Painting","1780");
        ArtworkDto artworkDto2 = new ArtworkDto(2,"Sunflower","1880");
        List<ArtworkDto> artworkDtoList = new ArrayList<>();
        artworkDtoList.add(artworkDto1);
        artworkDtoList.add(artworkDto2);
        List<Artwork> artworkList = new ArtworkConverter().dtoToEntity(artworkDtoList);
        assertEquals(2,artworkList.size());
    }

    @Test
    void entityToDto_convertsUsersEntityToUsersDto() {
        Users users = new Users("jack@artist.com", "abcdef12345", (short) 1);
        UsersDto usersDto = new UsersConverter().entityToDto(users);
        assertEquals("jack@artist.com",usersDto.getUsername());
    }

    @Test
    void DtoToEntity_convertsUsersDtoToUsersEntity() {
        UsersDto usersDto = new UsersDto("jack@artist.com", "abcdef12345", (short) 1);
        Users users = new UsersConverter().dtoToEntity(usersDto);
        assertEquals("jack@artist.com",users.getUsername());
    }

    @Test
    void entityToDto_convertsUsersEntityListToUsersDtoList() {
        Users users1 = new Users("jack@artist.com", "abcdef12345", (short) 1);
        Users users2 = new Users("jack@artist.com", "abcdef12345", (short) 1);
        List<Users> usersList = new ArrayList<>();
        usersList.add(users1);
        usersList.add(users2);
        List<UsersDto> usersDtoList = new UsersConverter().entityToDto(usersList);
        assertEquals(2,usersDtoList.size());
    }

    @Test
    void dtoToEntity_convertsUsersDtoListToUsersEntityList() {
        UsersDto usersDto1 = new UsersDto("jack@artist.com", "abcdef12345", (short) 1);
        UsersDto usersDto2 = new UsersDto("jack@artist.com", "abcdef12345", (short) 1);
        List<UsersDto> usersDtoList = new ArrayList<>();
        usersDtoList.add(usersDto1);
        usersDtoList.add(usersDto2);
        List<Users> usersList = new UsersConverter().dtoToEntity(usersDtoList);
        assertEquals(2,usersList.size());
    }


}
