package com.example.springboot.artgallery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.springboot.artgallery.dao.ArtistRepository;
import com.example.springboot.artgallery.dao.ArtworkRepository;
import com.example.springboot.artgallery.dao.AuthoritiesRepository;
import com.example.springboot.artgallery.dao.UsersRepository;
import com.example.springboot.artgallery.entity.Artist;
import com.example.springboot.artgallery.entity.Artwork;
import com.example.springboot.artgallery.entity.Authorities;
import com.example.springboot.artgallery.entity.Users;
import com.example.springboot.artgallery.exception.MyException;
import com.example.springboot.artgallery.service.ArtistService;

import com.example.springboot.artgallery.service.ArtworkService;
import com.example.springboot.artgallery.service.AuthoritiesService;
import com.example.springboot.artgallery.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class ArtGalleryApplicationTests {

	@Autowired
	private ArtistService artistService;

	@Autowired
	private ArtworkService artworkService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private AuthoritiesService authoritiesService;

	@MockBean
	private ArtistRepository artistRepository;

	@MockBean
	private ArtworkRepository artworkRepository ;

	@MockBean
	private UsersRepository usersRepository;

	@MockBean
	private AuthoritiesRepository authoritiesRepository;


	@Test
	void artistId_Test() {
		Artist artist=new Artist();
		artist.setId(1);
		assertEquals(1,artist.getId());
	}

	@Test
	void firstName_Test() {
		Artist artist=new Artist();
		artist.setFirstName("Jack");
		assertEquals("Jack",artist.getFirstName());
	}

	@Test
	void lastName_Test() {
		Artist artist=new Artist();
		artist.setLastName("Smith");
		assertEquals("Smith",artist.getLastName());
	}

	@Test
	void email_Test() {
		Artist artist=new Artist();
		artist.setEmail("jack@artist.com");
		assertEquals("jack@artist.com",artist.getEmail());
	}

	@Test
	void age_Test() {
		Artist artist=new Artist();
		artist.setAge("39");
		assertEquals("39",artist.getAge());
	}

	@Test
	void birthPlace_Test() {
		Artist artist=new Artist();
		artist.setBirthPlace("USA");
		assertEquals("USA",artist.getBirthPlace());
	}

	@Test
	void artStyle_Test() {
		Artist artist=new Artist();
		artist.setArtStyle("Contemporary");
		assertEquals("Contemporary",artist.getArtStyle());
	}

	@Test
	void artworkArtist_Test() {
		Artist artist=new Artist();
		Artwork theArtwork1 = new Artwork(1,"Painting","1780");
		Artwork theArtwork2 = new Artwork(2,"Sunflower","1880");
		List<Artwork> artwork = new ArrayList<>();
		artwork.add(theArtwork1);
		artist.setArtworks(artwork);
		artist.addArtwork(theArtwork2);
		assertEquals(2,artist.getArtworks().size());
	}

	@Test
	void addArtworkArtist_Test() {
		Artist artist = new Artist("Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
		Artwork artwork = new Artwork(1,"Painting","1780");
		artist.addArtwork(artwork);
		assertEquals(1,artist.getArtworks().size());
	}

	@Test
	void toStringArtist_ConvertsToString() {
		assertFalse(new Artist().toString().contains("@$"));
	}

	@Test
	void findAll_getAllArtists() {
		when(artistRepository.findAll()).thenReturn(Stream.of(
				new Artist("Kunal", "Sharma", "kunal@artist.com", "39", "USA", "Contemporary"),
				new Artist("Pavan", "Kumar", "pavan@artist.com", "28", "Italy", "Cubism")
		).collect(Collectors.toList()));
		assertEquals(2,artistService.findAllArtists().size());
	}

	@Test
	void findById_getArtistWithId() {
		Artist artist = new Artist(1,"Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
		Optional<Artist> artistById = Optional.of(artist);
		when(artistRepository.findById(1)).thenReturn(artistById);
		assertEquals(artistService.findArtistById(1),artist);
	}

	@Test
	void findById_exceptionTest() {
		Exception exception = assertThrows(MyException.class, () -> {
			artistService.findArtistById(2);
		});
		String expectedMessage = "Did not find Artist id - 2";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	void findByEmail_getArtistWithEmail() {
		Artist artist = new Artist("Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
		Optional<Artist> artistByEmail = Optional.of(artist);
		when(artistRepository.findByEmail("jack@artist.com")).thenReturn(artistByEmail);
		assertEquals(artistService.findArtistByEmail("jack@artist.com"),artist);
	}

	@Test
	void findByEmail_exceptionTest() {
		Exception exception = assertThrows(MyException.class, () -> {
			artistService.findArtistByEmail("jack@artist.com");
		});
		String expectedMessage = "Did not find Artist - jack@artist.com";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	void save_saveArtist() {
		Artist artist = new Artist("Jack", "Smith", "jack@artist.com", "39", "USA", "Contemporary");
		artistService.saveArtist(artist);
		verify(artistRepository,times(1)).save(artist);
	}

	@Test
	void deleteById_deleteArtistWithId() {
		artistService.deleteArtistById(1);
		verify(artistRepository,times(1)).deleteById(1);
	}

	@Test
	void artworkId_Test() {
		Artwork artwork = new Artwork();
		artwork.setId(1);
		assertEquals(1, artwork.getId());
	}

	@Test
	void title_Test() {
		Artwork artwork = new Artwork();
		artwork.setTitle("Painting");
		assertEquals("Painting",artwork.getTitle());
	}

	@Test
	void year_Test() {
		Artwork artwork = new Artwork();
		artwork.setYear("1780");
		assertEquals("1780",artwork.getYear());
	}

	@Test
	void findArtistById_getArtworkWithId() {
		Artwork artwork = new Artwork(1,"Painting","1780");
		Optional<Artwork> artworkById = Optional.of(artwork);
		when(artworkRepository.findById(1)).thenReturn(artworkById);
		assertEquals(artworkService.findArtworkById(1),artwork);
	}

	@Test
	void findArtworkById_exceptionTest() {
		Exception exception = assertThrows(MyException.class, () -> {
			artworkService.findArtworkById(2);
		});
		String expectedMessage = "Did not find Artwork id - 2";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage,actualMessage);
	}

	@Test
	void toStringArtwork_ConvertsToString() {
		assertFalse(new Artwork().toString().contains("@$"));
	}

	@Test
	void save_saveArtwork() {
		Artwork artwork = new Artwork("Painting","1780");
		artworkService.saveArtwork(artwork);
		verify(artworkRepository,times(1)).save(artwork);
	}

	@Test
	void delete_deleteArtworkWithId() {
		artworkService.deleteArtworkById(1);
		verify(artworkRepository,times(1)).deleteById(1);
	}

	@Test
	void usersUsername_Test() {
		Users users = new Users();
		users.setUsername("jack@artist.com");
		assertEquals("jack@artist.com", users.getUsername());
	}

	@Test
	void password_Test() {
		Users users = new Users();
		users.setPassword("abcdef12345");
		assertEquals("abcdef12345", users.getPassword());
	}

	@Test
	void enabled_Test() {
		Users users = new Users();
		users.setEnabled((short) 1);
		assertEquals(1, users.getEnabled());
	}

	@Test
	void userAuthority_Test() {
		Users users=new Users();
		Authorities authority1 = new Authorities("jack@artist.com","ROLE_ARTIST");
		Authorities authority2 = new Authorities("jack@artist.com","ROLE_ARTIST");
		List<Authorities> authorities = new ArrayList<>();
		authorities.add(authority1);
		users.setAuthorities(authorities);
		users.addAuthority(authority2);
		assertEquals(2,users.getAuthorities().size());
	}

	@Test
	void addAuthorityUsers_Test() {
		Users users = new Users("jack@artist.com", "abcdef12345", (short) 1);
		Authorities authority = new Authorities("jack@artist.com","ROLE_ARTIST");
		users.addAuthority(authority);
		assertEquals(1,users.getAuthorities().size());
	}

	@Test
	void toStringUsers_ConvertsToString() {
		assertFalse(new Users().toString().contains("@$"));
	}

	@Test
	void findByUsername_getUserWithEmail() {
		Users users = new Users("jack@artist.com", "abcdef12345", (short) 1);
		Optional<Users> userByEmail = Optional.of(users);
		when(usersRepository.findByUsername("jack@artist.com")).thenReturn(userByEmail);
		assertEquals(usersService.findByUsername("jack@artist.com"),users);
	}

	@Test
	void findByUsername_exceptionTest() {
		Exception exception = assertThrows(MyException.class, () -> {
			usersService.findByUsername("jack@artist.com");
		});
		String expectedMessage = "Did not find User - jack@artist.com";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage,actualMessage);
	}


	@Test
	void save_saveUser() {
		Users users = new Users("jack@artist.com", "abcdef12345", (short) 1);
		usersService.save(users);
		verify(usersRepository,times(1)).save(users);
	}

	@Test
	void delete_deleteUserWithId() {
		usersService.deleteByUsername("jack@artist.com");
		verify(usersRepository,times(1)).deleteByUsername("jack@artist.com");
	}

	@Test
	void usernameAuthority_Test() {
		Authorities authorities = new Authorities();
		authorities.setUsername("jack@artist.com");
		assertEquals("jack@artist.com", authorities.getUsername());
	}

	@Test
	void authority_Test() {
		Authorities authorities = new Authorities();
		authorities.setAuthority("ROLE_ARTIST");
		assertEquals("ROLE_ARTIST", authorities.getAuthority());
	}

	@Test
	void toStringAuthorities_ConvertsToString() {
		assertFalse(new Authorities().toString().contains("@$"));
	}

	@Test
	void save_saveAuthorities() {
		Authorities authorities = new Authorities("jack@artist.com","ROLE_ARTIST");
		authoritiesService.save(authorities);
		verify(authoritiesRepository,times(1)).save(authorities);
	}

}
