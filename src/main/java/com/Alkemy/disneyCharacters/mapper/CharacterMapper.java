package com.Alkemy.disneyCharacters.mapper;

import com.Alkemy.disneyCharacters.dto.CharacterBasicDTO;
import com.Alkemy.disneyCharacters.dto.CharacterDTO;
import com.Alkemy.disneyCharacters.dto.FilmDTO;
import com.Alkemy.disneyCharacters.entity.CharacterEntity;
import com.Alkemy.disneyCharacters.entity.FilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    @Autowired FilmMapper filmMapper;

    public CharacterEntity characterDTO2Entity(CharacterDTO dto, boolean loadFilms) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setHistory(dto.getHistory());
        if (loadFilms) {
            List<FilmEntity> films = filmMapper.filmDTOList2EntityList(dto.getFilms());
            characterEntity.setFilms(films);
        }
        return characterEntity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadFilms) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory(entity.getHistory());
        if (loadFilms) {
            List<FilmDTO> filmDTO = filmMapper.filmEntityList2DTOList(entity.getFilms(),
                    false, false);
            dto.setFilms(filmDTO);
        }
        return dto;
    }

    public List<CharacterBasicDTO> characterEntityList2BasicDTOList(List<CharacterEntity> entities) {
        List<CharacterBasicDTO> dtos = new ArrayList<>();
        CharacterBasicDTO basicDTO;
        for (CharacterEntity entity : entities) {
            basicDTO = new CharacterBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setImage(entity.getImage());
            basicDTO.setName(entity.getName());
            dtos.add(basicDTO);
        }
        return dtos;
    }

    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities, boolean loadFilms) {
        List<CharacterDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            dtos.add(characterEntity2DTO(entity, loadFilms));
        }
        return dtos;
    }

    public List<CharacterEntity> characterDTOList2EntityList(List<CharacterDTO> dtos) {
        List<CharacterEntity> charactersEntities = new ArrayList<>();
        for (CharacterDTO dto : dtos){
            charactersEntities.add(characterDTO2Entity(dto, false));
        }
        return charactersEntities;
    }

    public void characterEntityRefreshValues(CharacterEntity characterEntity, CharacterDTO dto) {
        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setHistory(dto.getHistory());
    }
}
