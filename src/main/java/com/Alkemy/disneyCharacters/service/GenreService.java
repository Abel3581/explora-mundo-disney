package com.Alkemy.disneyCharacters.service;

import com.Alkemy.disneyCharacters.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    public GenreDTO save (GenreDTO dto);

    void delete(Long id);

    GenreDTO update(Long id, GenreDTO genreDTO);

    List<GenreDTO> getAllGenres();
}
