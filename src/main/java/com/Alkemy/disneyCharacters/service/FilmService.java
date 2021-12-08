package com.Alkemy.disneyCharacters.service;

import com.Alkemy.disneyCharacters.dto.FilmBasicDTO;
import com.Alkemy.disneyCharacters.dto.FilmDTO;

import java.util.List;
import java.util.Set;

public interface FilmService {

    public FilmDTO save (FilmDTO dto);

    List<FilmBasicDTO> getAllFilms();

    List<FilmDTO> getAllFilmsDetailed();

    void delete(Long id);

    FilmDTO update(Long id, FilmDTO filmDTO);

    List<FilmDTO> getByFilters(String name, Set<Long> idGenre, String order);
}
