package com.disney.demo.services.impl;

import com.disney.demo.models.entities.Character;
import com.disney.demo.models.views.CharacterDTO;
import com.disney.demo.repositorys.CharacterRepository;
import com.disney.demo.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharactersServiceImpl implements CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Override
    public List<CharacterDTO> getAllCharacters(String name, int age) {
        List<Character> characters = new ArrayList<>();
        List<CharacterDTO> charactersDTO = new ArrayList<>();
        if (!name.equals("") && age > 0) {
            characters = characterRepository.findByNameLikeAndAgeGreaterThanEqual(name, age);
        } else if (!name.equals("")) {
            characters = characterRepository.findByNameLike(name);
        } else if (age > 0) {
            characters = characterRepository.findByAgeGreaterThanEqual(age);
        } else
            characters = characterRepository.findAll();

        characters.forEach(character -> {
            charactersDTO.add(
                    CharacterDTO.builder()
                            .name(character.getName())
                            .age(character.getAge())
                            .movies(character.getPeliculas())
                            .build()
            );
        });

        return charactersDTO;
    }

    @Override
    public CharacterDTO getById(int characterId) {
        Character character = characterRepository.findById(characterId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("characterId %d doesnt exists", characterId)));
        return CharacterDTO.builder()
                .name(character.getName())
                .age(character.getAge())
                .movies(character.getPeliculas())
                .build();
    }

    @Override
    public CharacterDTO createCharacter(Character character) {
        characterRepository.save(character);
        return CharacterDTO.builder()
                .name(character.getName())
                .age(character.getAge())
                .movies(character.getPeliculas())
                .build();
    }

    @Override
    public CharacterDTO updateCharacter(Character character, int characterId) {
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
        return CharacterDTO.builder()
                .name(character.getName())
                .age(character.getAge())
                .movies(character.getPeliculas())
                .build();
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
