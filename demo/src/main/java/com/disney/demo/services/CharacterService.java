package com.disney.demo.services;

import com.disney.demo.models.entities.Character;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CharacterService {
    List<Character> getAllCharacters(String name, int age);

    Character getById(int id);

    Character createCharacter(Character character);

    Character updateCharacter(Character character, int characterId);

    void deleteCharacter(int characterId);
}
