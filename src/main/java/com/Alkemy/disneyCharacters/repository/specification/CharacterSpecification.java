package com.Alkemy.disneyCharacters.repository.specification;

import com.Alkemy.disneyCharacters.dto.CharacterFiltersDTO;
import com.Alkemy.disneyCharacters.entity.CharacterEntity;
import com.Alkemy.disneyCharacters.entity.FilmEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }
            if (!ObjectUtils.isEmpty(filtersDTO.getAge())) {
                Integer age = filtersDTO.getAge();
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), age)
                );
            }
            if (!ObjectUtils.isEmpty(filtersDTO.getIdFilm())) {
                Join<FilmEntity, CharacterEntity> join = root.join("films", JoinType.INNER);
                Expression<String> idFilm = join.get("id");
                predicates.add(idFilm.in(filtersDTO.getIdFilm()));
            }
            //Remove duplicates
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
