package com.Alkemy.disneyCharacters.mapper;

import com.Alkemy.disneyCharacters.dto.FilmDTO;
import com.Alkemy.disneyCharacters.dto.GenreDTO;
import com.Alkemy.disneyCharacters.entity.FilmEntity;
import com.Alkemy.disneyCharacters.entity.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    @Autowired
    private FilmMapper filmMapper;

    public GenreEntity genreDTO2Entity (GenreDTO dto, boolean loadFilms){
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setName(dto.getName());
        genreEntity.setImage(dto.getImage());
        if (loadFilms) {
            List<FilmEntity> films = filmMapper.filmDTOList2EntityList(dto.getFilms());
            genreEntity.setFilms(films);
        }
        return genreEntity;
    }

    public GenreDTO genreEntity2DTO (GenreEntity entity, boolean loadFilms){
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        if (loadFilms) {
            List<FilmDTO> filmsDTO = filmMapper.filmEntityList2DTOList(entity.getFilms(),
                    false, false);
            dto.setFilms(filmsDTO);
        }
        return dto;
    }

    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> entities, boolean loadFilms) {
        List<GenreDTO> dtos = new ArrayList<>();
        for (GenreEntity entity : entities) {
            dtos.add(genreEntity2DTO(entity, loadFilms));
        }
        return dtos;
    }

    public List<GenreEntity> genreDTOList2EntityList(List<GenreDTO> dtos) {
        List<GenreEntity> genresEntities = new ArrayList<>();
        for (GenreDTO dto : dtos){
            genresEntities.add(genreDTO2Entity(dto, false));
        }
        return genresEntities;
    }

    public void genreEntityRefreshValues(GenreEntity entity, GenreDTO genreDTO) {
        entity.setName(genreDTO.getName());
        entity.setImage(genreDTO.getImage());
    }
}
