package com.example.springboot.artgallery;

import com.example.springboot.artgallery.controller.ArtworkController;
import com.example.springboot.artgallery.dao.ArtistRepository;
import com.example.springboot.artgallery.dao.ArtworkRepository;
import com.example.springboot.artgallery.dto.ArtistDto;
import com.example.springboot.artgallery.dto.ArtworkDto;
import com.example.springboot.artgallery.entity.Artist;
import com.example.springboot.artgallery.entity.Artwork;
import com.example.springboot.artgallery.service.ArtistService;
import com.example.springboot.artgallery.service.ArtworkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
class ArtworkControllerTests {
    private MockMvc mockMvc;

    ArtworkController artworkController;

    @Autowired
    ArtworkService artworkService;

    @Autowired
    ArtistService artistService;

    @MockBean
    ArtworkRepository artworkRepository;

    @MockBean
    ArtistRepository artistRepository;

    @BeforeEach
    void setup()
    {
        artworkController = new ArtworkController(artworkService, artistService);
        mockMvc= MockMvcBuilders.standaloneSetup(artworkController).build();
    }

    @Test
    void listArtworks_displaysAllArtists() throws Exception {

        Artwork artwork1 = new Artwork(1,"Painting","1780");
        Artwork artwork2 = new Artwork(2,"Sunflower","1880");

        Artist artist = new Artist(1,"Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
        artist.setArtworks(Arrays.asList(artwork1, artwork2));

        Optional<Artist> artistById = Optional.of(artist);
        when(artistRepository.findById(1)).thenReturn(artistById);

        this.mockMvc.perform(get("/artworks/list?artistId={artistId}",1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("artworks",hasSize(2)))
                .andExpect(view().name("artworks/list-artworks"));
    }

    @Test
    void showFormForAdd_displayArtistForm() throws Exception {

        this.mockMvc.perform(get("/artworks/showFormForAdd?artistId={artistId}",1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("artwork",hasProperty("id",is(0))))
                .andExpect(view().name("artworks/artwork-form"));
    }

    @Test
    void showFormForUpdate_displayArtworkForm() throws Exception {

        Artwork artwork = new Artwork(1,"Sunflower","1880");

        Optional<Artwork> artworkById = Optional.of(artwork);
        when(artworkRepository.findById(1)).thenReturn(artworkById);

        this.mockMvc.perform(get("/artworks/showFormForUpdate?artistId={artistId}&artworkId={artworkId}",1,1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("artwork",hasProperty("id",is(1))))
                .andExpect(view().name("artworks/artwork-form"));
    }

    @Test
    void save_addArtwork() throws Exception {

        Artist artist = new Artist(1,"Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
        Optional<Artist> artistById = Optional.of(artist);
        when(artistRepository.findById(1)).thenReturn(artistById);

        Artwork artwork = new Artwork("Sunflower","1880");
        artworkService.saveArtwork(artwork);

        this.mockMvc.perform(post("/artworks/save?artistId={artistId}",1)
                        .param("title", "Sunflower")
                        .param("year","1880")
                        .sessionAttr("artwork",new ArtworkDto()))
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:/artworks/list?artistId=1"))
                .andExpect(redirectedUrl("/artworks/list?artistId=1"));
    }

    @Test
    void save_addArtworkValidationError() throws Exception {

        Artist artist = new Artist(1,"Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
        Optional<Artist> artistById = Optional.of(artist);
        when(artistRepository.findById(1)).thenReturn(artistById);

        this.mockMvc.perform(post("/artworks/save?artistId={artistId}",1)
                        .param("title", "S")
                        .param("year","18")
                        .sessionAttr("artwork",new ArtworkDto()))
                .andExpect(status().isOk())
                .andExpect(view().name("artworks/artwork-form"))
                .andExpect(model().attribute("artwork",hasProperty("id",is(0))));
    }

}
