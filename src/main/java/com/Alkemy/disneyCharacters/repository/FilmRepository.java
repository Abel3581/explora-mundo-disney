package com.Alkemy.disneyCharacters.repository;

import com.Alkemy.disneyCharacters.entity.FilmEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long>, JpaSpecificationExecutor<FilmEntity> {
    List<FilmEntity> findAll(Specification<FilmEntity> spec);
}
