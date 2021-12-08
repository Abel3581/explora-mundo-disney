package com.Alkemy.disneyCharacters.dto;

import com.Alkemy.disneyCharacters.entity.FilmEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterBasicDTO {
    private Long id;
    private String image;
    private String name;
}
