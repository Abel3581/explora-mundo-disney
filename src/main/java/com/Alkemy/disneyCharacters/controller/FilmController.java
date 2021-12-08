package com.Alkemy.disneyCharacters.controller;

import com.Alkemy.disneyCharacters.dto.FilmBasicDTO;
import com.Alkemy.disneyCharacters.dto.FilmDTO;
import com.Alkemy.disneyCharacters.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("films")

public class FilmController {

    @Autowired
    private FilmService filmService;

    @PostMapping
    public ResponseEntity<FilmDTO> save(@RequestBody FilmDTO film) {
        FilmDTO filmSaved = filmService.save(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmSaved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FilmBasicDTO>> getAll() {
        List<FilmBasicDTO> films = filmService.getAllFilms();
        return ResponseEntity.ok().body(films);
    }

    @GetMapping("/detailed")
    public ResponseEntity<List<FilmDTO>> getAllDetailed() {
        List<FilmDTO> films = filmService.getAllFilmsDetailed();
        return ResponseEntity.ok().body(films);
    }

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Set<Long> idGenre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<FilmDTO> films = filmService.getByFilters(title, idGenre, order);
        return ResponseEntity.ok(films);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        filmService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> update(@PathVariable Long id, @RequestBody FilmDTO filmDTO) {
        FilmDTO result = filmService.update(id, filmDTO);
        return ResponseEntity.ok().body(result);
    }
}
