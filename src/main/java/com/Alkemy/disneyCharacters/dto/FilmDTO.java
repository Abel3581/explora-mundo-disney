package com.Alkemy.disneyCharacters.dto;

import com.Alkemy.disneyCharacters.entity.CharacterEntity;
import com.Alkemy.disneyCharacters.entity.GenreEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class FilmDTO {
    private Long id;
    private String image;
    private String title;
    private String creationDate;
    private Integer qualification;
    private List<GenreDTO> genres;
    private List<CharacterDTO> characters;
}
