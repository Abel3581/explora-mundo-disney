package com.Alkemy.disneyCharacters.service.impl;

import com.Alkemy.disneyCharacters.dto.CharacterBasicDTO;
import com.Alkemy.disneyCharacters.dto.CharacterDTO;
import com.Alkemy.disneyCharacters.dto.CharacterFiltersDTO;
import com.Alkemy.disneyCharacters.entity.CharacterEntity;
import com.Alkemy.disneyCharacters.exception.ParamNotFound;
import com.Alkemy.disneyCharacters.mapper.CharacterMapper;
import com.Alkemy.disneyCharacters.repository.CharacterRepository;
import com.Alkemy.disneyCharacters.repository.specification.CharacterSpecification;
import com.Alkemy.disneyCharacters.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterSpecification characterSpecification;

    public CharacterDTO save (CharacterDTO dto){
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto, false);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, false);
        return result;
    }

    public List<CharacterBasicDTO> getAllIcons() {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterBasicDTO> result = characterMapper.characterEntityList2BasicDTOList(entities);
        return result;
    }

    public List<CharacterDTO> getAllIconsDetailed() {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterDTO> result = characterMapper.characterEntityList2DTOList(entities, true);
        return result;
    }

    public void delete(Long id) { characterRepository.deleteById(id); }

    public CharacterDTO update(Long id, CharacterDTO characterDTO) {
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Error: Invalid character id");
        }
        characterMapper.characterEntityRefreshValues(entity.get(), characterDTO);
        CharacterEntity entitySaved = characterRepository.save(entity.get());
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, false);
        return result;
    }

    public List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> idFilm) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, idFilm);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        List<CharacterDTO> dtos = characterMapper.characterEntityList2DTOList(entities, true);
        return dtos;
    }
}
