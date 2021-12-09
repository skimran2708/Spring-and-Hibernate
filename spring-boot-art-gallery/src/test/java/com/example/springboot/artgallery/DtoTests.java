package com.example.springboot.artgallery;

import com.example.springboot.artgallery.dto.ArtistDto;
import com.example.springboot.artgallery.dto.ArtworkDto;
import com.example.springboot.artgallery.dto.UsersDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DtoTests {

    @Test
    void artistId_Test() {
        ArtistDto artistDto=new ArtistDto();
        artistDto.setId(1);
        assertEquals(1,artistDto.getId());
    }

    @Test
    void firstName_Test() {
        ArtistDto artistDto=new ArtistDto();
        artistDto.setFirstName("Jack");
        assertEquals("Jack",artistDto.getFirstName());
    }

    @Test
    void lastName_Test() {
        ArtistDto artistDto=new ArtistDto();
        artistDto.setLastName("Smith");
        assertEquals("Smith",artistDto.getLastName());
    }

    @Test
    void email_Test() {
        ArtistDto artistDto=new ArtistDto();
        artistDto.setEmail("jack@artist.com");
        assertEquals("jack@artist.com",artistDto.getEmail());
    }

    @Test
    void age_Test() {
        ArtistDto artistDto=new ArtistDto();
        artistDto.setAge("39");
        assertEquals("39",artistDto.getAge());
    }

    @Test
    void birthPlace_Test() {
        ArtistDto artistDto=new ArtistDto();
        artistDto.setBirthPlace("USA");
        assertEquals("USA",artistDto.getBirthPlace());
    }

    @Test
    void artStyle_Test() {
        ArtistDto artistDto=new ArtistDto();
        artistDto.setArtStyle("Contemporary");
        assertEquals("Contemporary",artistDto.getArtStyle());
    }

    @Test
    void artistDtoConstructor_Test() {
        ArtistDto artistDto =new ArtistDto(1,"Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
        assertEquals("Jack",artistDto.getFirstName());
    }

    @Test
    void artworkId_Test() {
        ArtworkDto artworkDto = new ArtworkDto();
        artworkDto.setId(1);
        assertEquals(1, artworkDto.getId());
    }

    @Test
    void title_Test() {
        ArtworkDto artworkDto = new ArtworkDto();
        artworkDto.setTitle("Painting");
        assertEquals("Painting",artworkDto.getTitle());
    }

    @Test
    void year_Test() {
        ArtworkDto artworkDto = new ArtworkDto();
        artworkDto.setYear("1780");
        assertEquals("1780",artworkDto.getYear());
    }

    @Test
    void artworkDtoConstructor_Test() {
        ArtworkDto artworkDto = new ArtworkDto(1,"Painting","1780");
        assertEquals("Painting",artworkDto.getTitle());
    }

    @Test
    void usersUsername_Test() {
        UsersDto usersDto = new UsersDto();
        usersDto.setUsername("jack@artist.com");
        assertEquals("jack@artist.com", usersDto.getUsername());
    }

    @Test
    void password_Test() {
        UsersDto usersDto = new UsersDto();
        usersDto.setPassword("abcdef12345");
        assertEquals("abcdef12345", usersDto.getPassword());
    }

    @Test
    void enabled_Test() {
        UsersDto usersDto = new UsersDto();
        usersDto.setEnabled((short) 1);
        assertEquals(1, usersDto.getEnabled());
    }

    @Test
    void usersDtoConstructor_Test() {
        UsersDto usersDto = new UsersDto("jack@artist.com", "abcdef12345", (short) 1);
        assertEquals("jack@artist.com",usersDto.getUsername());
    }
}
