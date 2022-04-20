package com.disney.demo.controllers;

import com.disney.demo.models.entities.Character;
import com.disney.demo.services.CharacterService;
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

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<Character>> getAllCharacters(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                            @RequestParam(value = "age", required = false, defaultValue = "0") int age) {
        return ResponseEntity.ok(characterService.getAllCharacters(name, age));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterByid(@PathVariable("id") int characterId) {
        return ResponseEntity.ok(characterService.getById(characterId));
    }

    @PostMapping
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) {
        return ResponseEntity.ok(characterService.createCharacter(character));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@RequestBody Character character, @PathVariable("id") int characterId) {
        return ResponseEntity.ok(characterService.updateCharacter(character, characterId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable("id") int characterId) {
        characterService.deleteCharacter(characterId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
