package com.example.springboot.artgallery.controller;

import com.example.springboot.artgallery.entity.Artist;
import com.example.springboot.artgallery.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/artists")
public class ArtistController {

    static List<String> artStyleList;
    static {
        artStyleList = new ArrayList<>();
        artStyleList.add("Choose a ArtStyle");
        artStyleList.add("Abstract Art");
        artStyleList.add("Contemporary Art");
        artStyleList.add("Cubism");
        artStyleList.add("Modern Art");
        artStyleList.add("Symbolism");
    }

    private ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService theArtistService) {
        artistService = theArtistService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listArtists(Model theModel) {

        // get artists from database
        List<Artist> theArtists = artistService.findAll();

        //add to spring model
        theModel.addAttribute("artists", theArtists);

        return "artists/list-artists";
    }

    @GetMapping("/showArtistDetails")
    public String showArtistDetails(@RequestParam("artistId") int theId,
                                    Model theModel) {

        // get the artist from the service
        Artist theArtist = artistService.findById(theId);

        // set artist as a model attribute to pre-populate the form
        theModel.addAttribute("artist", theArtist);

        // send over to our form
        return "artists/artist-details";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Artist theArtist = new Artist();

        theModel.addAttribute("artist", theArtist);

        theModel.addAttribute("artStyleList", artStyleList);

        return "artists/artist-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("artistId") int theId,
                                          Model theModel) {

        // get the artist from the service
        Artist theArtist = artistService.findById(theId);

        // set artist as a model attribute to pre-populate the form
        theModel.addAttribute("artist", theArtist);

        theModel.addAttribute("artStyleList", artStyleList);

        // send over to our form
        return "artists/artist-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("artist") Artist theArtist, BindingResult bindingResult, Model theModel) {

        if (bindingResult.hasErrors()) {
            theModel.addAttribute("artStyleList", artStyleList);
            return "artists/artist-form";
        }


        else {
            // save the artist
            artistService.save(theArtist);

            // use a redirect to prevent duplicate submissions
            return "redirect:/artists/list";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("artistId") int theId) {

        // delete the artist
        artistService.deleteById(theId);

        // redirect
        return "redirect:/artists/list";

    }
}
