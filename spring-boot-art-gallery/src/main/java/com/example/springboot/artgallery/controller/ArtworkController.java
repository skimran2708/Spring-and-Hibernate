package com.example.springboot.artgallery.controller;

import com.example.springboot.artgallery.converter.ArtworkConverter;
import com.example.springboot.artgallery.dto.ArtworkDto;
import com.example.springboot.artgallery.entity.Artwork;
import com.example.springboot.artgallery.entity.Artist;
import com.example.springboot.artgallery.service.ArtworkService;
import com.example.springboot.artgallery.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/artworks")
public class ArtworkController {
    private final ArtworkService artworkService;
    private final ArtistService artistService;

    static final String VIEW_PAGE="artworks/artwork-form";

    @Autowired
    public ArtworkController(ArtworkService theArtworkService, ArtistService theArtistService) {
        artworkService = theArtworkService;
        artistService = theArtistService;
    }


    // add mapping for "/list"
    @GetMapping("/list")
    public String listArtworks(@RequestParam("artistId") int theArtistId,
                              Model theModel) {

        // get the artwork from the service
        Artist theArtist = artistService.findArtistById(theArtistId);
        List<Artwork> theArtworks = theArtist.getArtworks();

        // set artwork as a model attribute to pre-populate the form
        theModel.addAttribute("artworks", theArtworks);

        // send over to our form
        return "artworks/list-artworks";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(@RequestParam("artistId") int theArtistId, Model theModel) {

        // create model attribute to bind form data
        Artwork theArtwork = new Artwork();

        theModel.addAttribute("artwork", theArtwork);
        theModel.addAttribute("artistId",theArtistId);

        return VIEW_PAGE;
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("artistId") int theArtistId, @RequestParam("artworkId") int theId,
                                          Model theModel) {

        // get the artwork from the service
        Artwork theArtwork = artworkService.findArtworkById(theId);

        // set artist as a model attribute to pre-populate the form
        theModel.addAttribute("artwork", theArtwork);
        theModel.addAttribute("artistId",theArtistId);

        // send over to our form
        return VIEW_PAGE;
    }

    @PostMapping("/save")
    public String saveArtwork(@RequestParam("artistId") int theArtistId, @Valid @ModelAttribute("artwork") ArtworkDto artworkDto, BindingResult bindingResult) {

        // save the artWork

        if(bindingResult.hasErrors())
            return VIEW_PAGE;

        //convert dto to entity
        Artwork theArtwork = new ArtworkConverter().dtoToEntity(artworkDto);

        Artist theArtist = artistService.findArtistById(theArtistId);
        theArtist.addArtwork(theArtwork);

        artworkService.saveArtwork(theArtwork);

        // use a redirect to prevent duplicate submissions
        return "redirect:/artworks/list?artistId="+theArtistId;
    }

    @GetMapping("/delete")
    public String deleteArtwork(@RequestParam("artistId") int theArtistId, @RequestParam("artworkId") int theId) {

        // delete the artist
        artworkService.deleteArtworkById(theId);

        // redirect
        return "redirect:/artworks/list?artistId="+theArtistId;

    }
}
