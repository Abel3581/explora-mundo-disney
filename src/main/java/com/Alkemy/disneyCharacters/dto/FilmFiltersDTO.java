package com.Alkemy.disneyCharacters.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class FilmFiltersDTO {

    private String title;
    private Set<Long> idGenre;
    private String order;

    public FilmFiltersDTO(String title, Set<Long> idGenre, String order) {
        this.title = title;
        this.idGenre = idGenre;
        this.order = order;
    }

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }
}
