package com.disney.demo.services;

import com.disney.demo.models.entities.Character;
import com.disney.demo.models.views.CharacterDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CharacterService {
    List<CharacterDTO> getAllCharacters(String name, int age);

    CharacterDTO getById(int id);

    CharacterDTO createCharacter(Character character);

    CharacterDTO updateCharacter(Character character, int characterId);

    void deleteCharacter(int characterId);
}
