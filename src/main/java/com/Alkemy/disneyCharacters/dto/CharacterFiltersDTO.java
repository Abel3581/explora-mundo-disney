package com.Alkemy.disneyCharacters.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class CharacterFiltersDTO {

    private String name;
    private Integer age;
    private Set<Long> idFilm;

    public CharacterFiltersDTO(String name, Integer age, Set<Long> idFilm) {
        this.name = name;
        this.age = age;
        this.idFilm = idFilm;
    }
}
