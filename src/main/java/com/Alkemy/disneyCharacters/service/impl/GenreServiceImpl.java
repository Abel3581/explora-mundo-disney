package com.Alkemy.disneyCharacters.service.impl;

import com.Alkemy.disneyCharacters.dto.FilmDTO;
import com.Alkemy.disneyCharacters.dto.GenreDTO;
import com.Alkemy.disneyCharacters.entity.FilmEntity;
import com.Alkemy.disneyCharacters.entity.GenreEntity;
import com.Alkemy.disneyCharacters.exception.ParamNotFound;
import com.Alkemy.disneyCharacters.mapper.GenreMapper;
import com.Alkemy.disneyCharacters.repository.GenreRepository;
import com.Alkemy.disneyCharacters.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepository genreRepository;

    public GenreDTO save (GenreDTO dto){
        GenreEntity entity = genreMapper.genreDTO2Entity(dto, false);
        GenreEntity entitySaved = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntity2DTO(entitySaved, false);
        return result;
    }

    public void delete(Long id) {genreRepository.deleteById(id);}

    public GenreDTO update(Long id, GenreDTO genreDTO) {
        Optional<GenreEntity> entity = genreRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Error: Invalid film id");
        }
        genreMapper.genreEntityRefreshValues(entity.get(), genreDTO);
        GenreEntity entitySaved = genreRepository.save(entity.get());
        GenreDTO result = genreMapper.genreEntity2DTO(entitySaved, false);
        return result;
    }

    public List<GenreDTO> getAllGenres() {
        List<GenreEntity> entities = genreRepository.findAll();
        List<GenreDTO> result = genreMapper.genreEntityList2DTOList(entities, false);
        return result;
    }
}
