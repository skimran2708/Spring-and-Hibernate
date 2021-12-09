package com.example.springboot.artgallery.controller;

import com.example.springboot.artgallery.converter.UsersConverter;
import com.example.springboot.artgallery.dto.UsersDto;
import com.example.springboot.artgallery.entity.Artist;
import com.example.springboot.artgallery.entity.Authorities;
import com.example.springboot.artgallery.entity.Users;
import com.example.springboot.artgallery.service.ArtistService;
import com.example.springboot.artgallery.service.AuthoritiesService;
import com.example.springboot.artgallery.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    private final AuthoritiesService authoritiesService;
    private final ArtistService artistService;

    @Autowired
    public UsersController(UsersService theUsersService, AuthoritiesService theAuthoritiesService, ArtistService theArtist) {
        usersService = theUsersService;
        authoritiesService = theAuthoritiesService;
        artistService = theArtist;
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Users theUser = new Users();

        theModel.addAttribute("user", theUser);

        return "/user-form";
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") UsersDto usersDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "/user-form";

        //convert dto to entity
        Users theUser = new UsersConverter().dtoToEntity(usersDto);

        Authorities theAuthorities = new Authorities();
        theAuthorities.setUsername(theUser.getUsername());
        theAuthorities.setAuthority("ROLE_ARTIST");

        // save the user and authority
        theUser.setEnabled((short) 1);
        theUser.addAuthority(theAuthorities);
        usersService.save(theUser);
        authoritiesService.save(theAuthorities);

        // use a redirect to prevent duplicate submissions
        return "redirect:/loginPage?user=true";
    }

    @GetMapping("/showArtistDetails")
    public String showArtistDetails(Authentication authentication) {

        String username = authentication.getName();
        // get the artist from the service
        Artist theArtist = artistService.findArtistByEmail(username);

        // send over to our form
        return "redirect:/artists/showArtistDetails?artistId=" + theArtist.getId();
    }
}
