package com.Alkemy.disneyCharacters.mapper;

import com.Alkemy.disneyCharacters.dto.CharacterDTO;
import com.Alkemy.disneyCharacters.dto.FilmBasicDTO;
import com.Alkemy.disneyCharacters.dto.FilmDTO;
import com.Alkemy.disneyCharacters.dto.GenreDTO;
import com.Alkemy.disneyCharacters.entity.CharacterEntity;
import com.Alkemy.disneyCharacters.entity.FilmEntity;
import com.Alkemy.disneyCharacters.entity.GenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class FilmMapper {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private CharacterMapper characterMapper;

    public FilmEntity filmDTO2Entity(FilmDTO dto, boolean loadCharacters, boolean loadGenres) {
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setImage(dto.getImage());
        filmEntity.setTitle(dto.getTitle());
        filmEntity.setCreationDate(string2LocalDate(dto.getCreationDate()));
        filmEntity.setQualification(dto.getQualification());
        if (loadGenres) {
            List<GenreEntity> genresEntity = genreMapper.genreDTOList2EntityList(dto.getGenres());
            filmEntity.setGenres(genresEntity);
        }
        if (loadCharacters) {
            List<CharacterEntity> charactersEntities = characterMapper.characterDTOList2EntityList(dto.getCharacters());
            filmEntity.setCharacters(charactersEntities);
        }
        return filmEntity;
    }

    public FilmDTO filmEntity2DTO(FilmEntity entity, boolean loadCharacters, boolean loadGenres) {
        FilmDTO dto = new FilmDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setQualification(entity.getQualification());
        if (loadGenres) {
            List<GenreDTO> genresDTOS = genreMapper.genreEntityList2DTOList(entity.getGenres(), false);
            dto.setGenres(genresDTOS);
        }
        if (loadCharacters) {
            List<CharacterDTO> charactersDTOS = characterMapper.characterEntityList2DTOList(entity.getCharacters(), false);
            dto.setCharacters(charactersDTOS);
        }
        return dto;
    }

    public List<FilmDTO> filmEntityList2DTOList(List<FilmEntity> entities, boolean loadCharacters, boolean loadGenres) {
        List<FilmDTO> dtos = new ArrayList<>();
        for (FilmEntity entity : entities) {
            dtos.add(filmEntity2DTO(entity, loadCharacters, loadGenres));
        }
        return dtos;
    }

    public List<FilmEntity> filmDTOList2EntityList(List<FilmDTO> dtos) {
        List<FilmEntity> paisEntities = new ArrayList<>();
        for (FilmDTO dto : dtos){
            paisEntities.add(filmDTO2Entity(dto, false, false));
        }
        return paisEntities;
    }

    private LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    public List<FilmBasicDTO> filmEntityList2BasicDTOList(List<FilmEntity> entities) {
        List<FilmBasicDTO> dtos = new ArrayList<>();
        FilmBasicDTO basicDTO;
        for (FilmEntity entity : entities) {
            basicDTO = new FilmBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setImage(entity.getImage());
            basicDTO.setTitle(entity.getTitle());
            basicDTO.setCreationDate(entity.getCreationDate().toString());
            dtos.add(basicDTO);
        }
        return dtos;
    }

    public void filmEntityRefreshValues(FilmEntity filmEntity, FilmDTO dto) {
        filmEntity.setImage(dto.getImage());
        filmEntity.setTitle(dto.getTitle());
        filmEntity.setCreationDate(string2LocalDate(dto.getCreationDate()));
        filmEntity.setQualification(dto.getQualification());
    }
}
