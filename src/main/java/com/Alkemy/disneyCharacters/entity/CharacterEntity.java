package com.Alkemy.disneyCharacters.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "charac")
@Getter
@Setter
@SQLDelete(sql = "UPDATE charac SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")

public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;
    private String name;
    private Integer age;
    private Integer weight;
    private String history;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    private List<FilmEntity> films = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;
}
