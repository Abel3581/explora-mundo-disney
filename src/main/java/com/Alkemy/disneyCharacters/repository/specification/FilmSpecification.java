package com.Alkemy.disneyCharacters.repository.specification;

import com.Alkemy.disneyCharacters.dto.FilmFiltersDTO;
import com.Alkemy.disneyCharacters.entity.FilmEntity;
import com.Alkemy.disneyCharacters.entity.GenreEntity;
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
public class FilmSpecification {

    public Specification<FilmEntity> getByFilters(FilmFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(filtersDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }
            if (!ObjectUtils.isEmpty(filtersDTO.getIdGenre())) {
                Join<FilmEntity, GenreEntity> join = root.join("genres", JoinType.INNER);
                Expression<String> idGenre = join.get("id");
                predicates.add(idGenre.in(filtersDTO.getIdGenre()));
            }
            //Remove duplicates
            query.distinct(true);

            //orden
            String orderByField = "title";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
