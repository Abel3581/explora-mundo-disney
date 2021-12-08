package com.Alkemy.disneyCharacters.controller;

import com.Alkemy.disneyCharacters.dto.CharacterBasicDTO;
import com.Alkemy.disneyCharacters.dto.CharacterDTO;
import com.Alkemy.disneyCharacters.service.impl.CharacterServiceImpl;
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
@RequestMapping("characters")

public class CharacterController {

    @Autowired
    private CharacterServiceImpl characterService;

    @PostMapping
    public ResponseEntity<CharacterDTO> save (@RequestBody CharacterDTO character){
        CharacterDTO characterSaved = characterService.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CharacterBasicDTO>> getAll() {
        List<CharacterBasicDTO> character = characterService.getAllIcons();
        return ResponseEntity.ok().body(character);
    }

    @GetMapping("/detailed")
    public ResponseEntity<List<CharacterDTO>> getAllDetailed() {
        List<CharacterDTO> character = characterService.getAllIconsDetailed();
        return ResponseEntity.ok().body(character);
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam (required = false) Integer age,
            @RequestParam (required = false) Set<Long> idFilm
    ){
        List<CharacterDTO> characters = characterService.getByFilters(name,age,idFilm);
        return ResponseEntity.ok(characters);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO characterDTO){
        CharacterDTO result = characterService.update(id, characterDTO);
        return ResponseEntity.ok().body(result);
    }
}
