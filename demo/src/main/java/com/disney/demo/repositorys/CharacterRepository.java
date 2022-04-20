package com.disney.demo.repositorys;

import com.disney.demo.models.entities.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {
    List<Character> findByNameLikeAndAgeGreaterThanEqual(String name, int age);

    List<Character> findByNameLike(String name);

    List<Character> findByAgeGreaterThanEqual(int age);
}
