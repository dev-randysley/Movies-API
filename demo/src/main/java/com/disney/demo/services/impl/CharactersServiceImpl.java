package com.disney.demo.services.impl;

import com.disney.demo.models.entities.Character;
import com.disney.demo.repositorys.CharacterRepository;
import com.disney.demo.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CharactersServiceImpl implements CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Override
    public List<Character> getAllCharacters(String name, int age) {
        if (!name.isBlank() && age > 0) {
            return characterRepository.findByNameLikeAndAgeGreaterThanEqual(name, age);
        } else if (!name.isBlank()) {
            return characterRepository.findByNameLike(name);
        } else if (age > 0) {
            return characterRepository.findByAgeGreaterThanEqual(age);
        } else
            return characterRepository.findAll();
    }

    @Override
    public Character getById(int characterId) {
        return characterRepository.findById(characterId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("characterId %d doesnt exists", characterId)));
    }

    @Override
    public Character createCharacter(Character character) {
        return characterRepository.save(character);
    }

    @Override
    public Character updateCharacter(Character character, int characterId) {
        characterRepository.findById(characterId).ifPresentOrElse(
                oldCharacter -> {
                    oldCharacter.setPeliculas(character.getPeliculas());
                    oldCharacter.setPeso(character.getPeso());
                    oldCharacter.setName(character.getName());
                    oldCharacter.setHistoria(character.getHistoria());
                    oldCharacter.setAge(character.getAge());
                    characterRepository.save(oldCharacter);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("characterId %d doesnt exists", characterId));
                }
        );
        return character;
    }

    @Override
    public void deleteCharacter(int characterId) {
        characterRepository.findById(characterId).ifPresentOrElse(character -> characterRepository.delete(character),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("characterId %d doesnt exists", characterId));
                });
    }
}
