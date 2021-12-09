package com.example.springboot.artgallery.converter;

import com.example.springboot.artgallery.dto.ArtworkDto;
import com.example.springboot.artgallery.entity.Artwork;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper
public class ArtworkConverter {

    public ArtworkDto entityToDto(Artwork artwork) {

        ModelMapper mapper = new ModelMapper();
        return mapper.map(artwork,ArtworkDto.class);
    }

    public Artwork dtoToEntity(ArtworkDto artworkDto) {

        ModelMapper mapper = new ModelMapper();
        return mapper.map(artworkDto, Artwork.class);
    }

    public List<ArtworkDto> entityToDto(List<Artwork> artworkList) {

        return  artworkList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<Artwork> dtoToEntity(List<ArtworkDto> artworkDtoList){

        return artworkDtoList.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}
