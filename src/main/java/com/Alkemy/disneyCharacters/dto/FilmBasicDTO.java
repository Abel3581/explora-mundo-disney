package com.Alkemy.disneyCharacters.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class FilmBasicDTO {
    private Long id;
    private String image;
    private String title;
    private String creationDate;
}
