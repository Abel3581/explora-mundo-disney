package com.Alkemy.disneyCharacters.service.impl;

import com.Alkemy.disneyCharacters.dto.FilmBasicDTO;
import com.Alkemy.disneyCharacters.dto.FilmDTO;
import com.Alkemy.disneyCharacters.dto.FilmFiltersDTO;
import com.Alkemy.disneyCharacters.entity.FilmEntity;
import com.Alkemy.disneyCharacters.exception.ParamNotFound;
import com.Alkemy.disneyCharacters.mapper.FilmMapper;
import com.Alkemy.disneyCharacters.repository.FilmRepository;
import com.Alkemy.disneyCharacters.repository.specification.FilmSpecification;
import com.Alkemy.disneyCharacters.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FilmSpecification filmSpecification;

    public FilmDTO save(FilmDTO dto) {
        FilmEntity entity = filmMapper.filmDTO2Entity(dto, true, true);
        FilmEntity entitySaved = filmRepository.save(entity);
        FilmDTO result = filmMapper.filmEntity2DTO(entitySaved, true, true);
        return result;
    }

    public List<FilmBasicDTO> getAllFilms() {
        List<FilmEntity> entities = filmRepository.findAll();
        List<FilmBasicDTO> result = filmMapper.filmEntityList2BasicDTOList(entities);
        return result;
    }

    public List<FilmDTO> getAllFilmsDetailed() {
        List<FilmEntity> entities = filmRepository.findAll();
        List<FilmDTO> result = filmMapper.filmEntityList2DTOList(entities, true, false);
        return result;
    }

    public void delete(Long id) {
        filmRepository.deleteById(id);
    }

    public FilmDTO update(Long id, FilmDTO filmDTO) {
        Optional<FilmEntity> entity = filmRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Error: Invalid film id");
        }
        filmMapper.filmEntityRefreshValues(entity.get(), filmDTO);
        FilmEntity entitySaved = filmRepository.save(entity.get());
        FilmDTO result = filmMapper.filmEntity2DTO(entitySaved, false, false);
        return result;
    }

    public List<FilmDTO> getByFilters(String name, Set<Long> idGenre, String order) {
        FilmFiltersDTO filtersDTO = new FilmFiltersDTO(name, idGenre, order);
        List<FilmEntity> entities = filmRepository.findAll(filmSpecification.getByFilters(filtersDTO));
        List<FilmDTO> dtos = filmMapper.filmEntityList2DTOList(entities, false, true);
        return dtos;
    }
}
