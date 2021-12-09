package com.example.springboot.artgallery;

import com.example.springboot.artgallery.dto.UsersDto;
import org.springframework.security.core.Authentication;
import com.example.springboot.artgallery.controller.UsersController;
import com.example.springboot.artgallery.dao.ArtistRepository;
import com.example.springboot.artgallery.entity.Users;
import com.example.springboot.artgallery.service.ArtistService;
import com.example.springboot.artgallery.service.AuthoritiesService;
import com.example.springboot.artgallery.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.Optional;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class UsersControllerTests {

    private MockMvc mockMvc;

    UsersController usersController;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private AuthoritiesService authoritiesService;

    @Autowired
    private UsersService usersService;

    @MockBean
    private ArtistRepository artistRepository;

    @MockBean
    private Authentication authentication;

    @BeforeEach
    void setup()
    {
        usersController = new UsersController(usersService,authoritiesService,artistService);
        mockMvc= MockMvcBuilders.standaloneSetup(usersController).build();
    }


    @Test
    void showFormForAdd_displayUserForm() throws Exception {
        Users users=null;

        this.mockMvc.perform(get("/users/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("users",users))
                .andExpect(view().name("/user-form"));
    }

    @Test
    void save_addUser() throws Exception {

        this.mockMvc.perform(post("/users/save")
                        .param("username", "jack@artist.com")
                        .param("password", "abcdef12345")
                        //.param("enabled", String.valueOf(1))
                        .sessionAttr("user",new UsersDto()))
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:/loginPage?user=true"))
                .andExpect(redirectedUrl("/loginPage?user=true"));
    }


    @Test
    void save_addUserValidationError() throws Exception {

        this.mockMvc.perform(post("/users/save")
                        .param("username", "")
                        .param("password", "")
                        .sessionAttr("user",new UsersDto()))
                .andExpect(status().isOk())
                .andExpect(view().name("/user-form"))
                .andExpect(model().attribute("user",hasProperty("username",is(""))));
    }

}
