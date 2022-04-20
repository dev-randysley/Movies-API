package com.disney.demo.services.impl;

import com.disney.demo.models.entities.Gender;
import com.disney.demo.repositorys.GenderRepository;
import com.disney.demo.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    GenderRepository genderRepository;

    @Override
    public List<Gender> finAll() {
        return genderRepository.findAll();
    }

    @Override
    public Gender findById(int genderId) {
        return genderRepository.findById(genderId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("genderId %d doesn´t exists", genderId)));
    }

    @Override
    public Gender createGender(Gender gender) {
        return genderRepository.save(gender);
    }

    @Override
    public Gender updateGender(Gender gender, int genderId) {
        genderRepository.findById(genderId).ifPresentOrElse(
                oldGender -> {
                    oldGender.setMovies(gender.getMovies());
                    oldGender.setName(gender.getName());
                    genderRepository.save(oldGender);
                },
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("genderId %d doesn´t exists", genderId));
                }
        );

        return gender;
    }

    @Override
    public void deleteGender(int genderId) {
        genderRepository.findById(genderId).ifPresentOrElse(
                gender -> genderRepository.delete(gender),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("genderId %d doesn´t exits", genderId));
                }
        );
    }
}
