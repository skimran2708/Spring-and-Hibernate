package com.example.springboot.artgallery;

import com.example.springboot.artgallery.controller.ArtistController;
import com.example.springboot.artgallery.dao.ArtistRepository;
import com.example.springboot.artgallery.dto.ArtistDto;
import com.example.springboot.artgallery.entity.Artist;
import com.example.springboot.artgallery.service.ArtistService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ArtistControllerTests {

    private MockMvc mockMvc;

    ArtistController artistController;

    @Autowired
    ArtistService artistService;

    @MockBean
    ArtistRepository artistRepository;

    @BeforeEach
    void setup()
    {
        artistController = new ArtistController(artistService);
        mockMvc= MockMvcBuilders.standaloneSetup(artistController).build();
    }

    @Test
    void listArtists_displaysAllArtists() throws Exception {

        Artist artist1 = new Artist(1,"Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
        Artist artist2 = new Artist(2,"Joe", "Smith", "joe@artist.com", "39", "USA", "Contemporary");

        when(artistService.findAllArtists()).thenReturn(Arrays.asList(artist1, artist2));

        this.mockMvc.perform(get("/artists/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("artists",hasSize(2)))
                .andExpect(view().name("artists/list-artists"));
    }

    @Test
    void showArtistDetails_displayDetailsofAnArtist() throws Exception {

        Artist artist = new Artist(1,"Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
        Optional<Artist> artistById = Optional.of(artist);
        when(artistRepository.findById(1)).thenReturn(artistById);

        this.mockMvc.perform(get("/artists/showArtistDetails?artistId={artistId}",1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("artist",hasProperty("firstName",is("Jack"))))
                .andExpect(view().name("artists/artist-details"));
    }

    @Test
    void showFormForAdd_displayArtistForm() throws Exception {

        this.mockMvc.perform(get("/artists/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("artist",hasProperty("id",is(0))))
                .andExpect(view().name("artists/artist-form"));
    }

    @Test
    void showFormForUpdate_displayArtistForm() throws Exception {

        Artist artist = new Artist(1,"Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
        Optional<Artist> artistById = Optional.of(artist);
        when(artistRepository.findById(1)).thenReturn(artistById);

        this.mockMvc.perform(get("/artists/showFormForUpdate?artistId={artistId}",1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("artist",hasProperty("id",is(1))))
                .andExpect(view().name("artists/artist-form"));
    }

    @Test
    void save_addArtist() throws Exception {

        Artist artist = new Artist("Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
        artistService.saveArtist(artist);

        this.mockMvc.perform(post("/artists/save")
                        .param("firstName", "Jack")
                        .param("lastName", "Smith")
                        .param("email", "jack@artist.com")
                        .param("age", "39")
                        .param("birthPlace", "USA")
                        .param("artStyle", "Contemporary")
                        .sessionAttr("artist",new ArtistDto()))
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:/artists/list"))
                .andExpect(redirectedUrl("/artists/list"));
    }

    @Test
    void save_addArtistValidationError() throws Exception {

        this.mockMvc.perform(post("/artists/save"))
                .andExpect(status().isOk())
                .andExpect(view().name("artists/artist-form"))
                .andExpect(model().attribute("artist",hasProperty("id",is(0))));
    }

//    @Test
//    void delete_deleteArtist() throws Exception {
//        Artist artist = new Artist(1,"Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
//        artistService.deleteArtistById(artist.getId());
//
//        this.mockMvc.perform(delete("/artists/delete?artistId={artistId}",1))
//                .andExpect(status().isOk())
//                .andExpect(view().name("redirect:/artists/list"))
//                .andExpect(redirectedUrl("/artists/list"));
//    }

}
