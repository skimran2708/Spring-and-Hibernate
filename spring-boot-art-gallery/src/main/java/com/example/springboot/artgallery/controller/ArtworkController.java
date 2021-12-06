package com.example.springboot.artgallery.controller;

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
    private ArtworkService artworkService;
    private ArtistService artistService;

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
        Artist theArtist = artistService.findById(theArtistId);
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

        return "artworks/artwork-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("artistId") int theArtistId, @RequestParam("artworkId") int theId,
                                          Model theModel) {

        // get the artwork from the service
        Artwork theArtwork = artworkService.findById(theId);

        // set artist as a model attribute to pre-populate the form
        theModel.addAttribute("artwork", theArtwork);
        theModel.addAttribute("artistId",theArtistId);

        // send over to our form
        return "artworks/artwork-form";
    }

    @PostMapping("/save")
    public String save(@RequestParam("artistId") int theArtistId, @Valid @ModelAttribute("artwork") Artwork theArtwork, BindingResult bindingResult) {

        // save the artWork

        if(bindingResult.hasErrors())
            return "artworks/artwork-form";

        Artist theArtist = artistService.findById(theArtistId);
        theArtist.addArtwork(theArtwork);

        artworkService.save(theArtwork);

        // use a redirect to prevent duplicate submissions
        return "redirect:/artworks/list?artistId="+theArtistId;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("artistId") int theArtistId, @RequestParam("artworkId") int theId) {

        // delete the artist
        artworkService.deleteById(theId);

        // redirect
        return "redirect:/artworks/list?artistId="+theArtistId;

    }
}
