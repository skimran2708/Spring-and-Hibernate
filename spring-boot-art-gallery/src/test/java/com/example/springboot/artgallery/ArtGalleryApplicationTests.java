package com.example.springboot.artgallery;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.example.springboot.artgallery.dao.ArtistRepository;
import com.example.springboot.artgallery.entity.Artist;
import com.example.springboot.artgallery.service.ArtistService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtGalleryApplicationTests {

	@Autowired
	private ArtistService artistService;

	@MockBean
	private ArtistRepository artistRepository;

	@Test
	public void findAll_getAllArtists() {
		when(artistRepository.findAll()).thenReturn(Stream.of(
				new Artist("Jack", "Andrew", "jack@artist.com", "39", "USA", "Contemporary"),
				new Artist("Pavan", "Kumar", "pavan@artist.com", "28", "Italy", "Cubism")
		).collect(Collectors.toList()));
		assertEquals(2,artistService.findAll().size());
	}

	@Test
	public void findById_getAllArtistsById() {
		int id=1;
		//String firstName="Jack";
		when(artistRepository.findById(id)).thenReturn(
				new Artist("Jack", "Andrew", "jack@artist.com", "39", "USA", "Contemporary"));
		assertEquals(1,artistService.findById(id));
	}



}
