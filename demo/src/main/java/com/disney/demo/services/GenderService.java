package com.disney.demo.services;

import com.disney.demo.models.entities.Gender;

import java.util.List;

public interface GenderService {
    List<Gender> finAll();

    Gender findById(int genderId);

    Gender createGender(Gender gender);

    Gender updateGender(Gender gender, int genderId);

    void deleteGender(int genderId);
}
