package com.example.springboot.artgallery.converter;

import com.example.springboot.artgallery.dto.ArtistDto;
import com.example.springboot.artgallery.entity.Artist;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper
public class ArtistConvertor {

    public ArtistDto entityToDto(Artist artist) {

        ModelMapper mapper = new ModelMapper();
        return mapper.map(artist,ArtistDto.class);
    }

    public Artist dtoToEntity(ArtistDto artistDto) {

        ModelMapper mapper = new ModelMapper();
        return mapper.map(artistDto, Artist.class);
    }

    public List<ArtistDto> entityToDto(List<Artist> artistList) {

        return  artistList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<Artist> dtoToEntity(List<ArtistDto> artistDtoList){

        return artistDtoList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
