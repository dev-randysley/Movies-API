package com.disney.demo.controllers;

import com.disney.demo.models.entities.Gender;
import com.disney.demo.services.GenderService;
import com.disney.demo.services.impl.GenderServiceImpl;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/geners")
public class GenderController {

    @Autowired
    GenderService genderService;

    @GetMapping
    public ResponseEntity<List<Gender>> getAll() {
        return ResponseEntity.ok(genderService.finAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gender> getGenderById(int genderId) {
        return ResponseEntity.ok(genderService.findById(genderId));
    }

    @PostMapping
    public ResponseEntity<Gender> createGender(@RequestBody Gender gender) {
        return ResponseEntity.ok(genderService.createGender(gender));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gender> updateGender(@RequestBody Gender gender, @PathVariable("id") int genderId) {
        return ResponseEntity.ok(genderService.updateGender(gender, genderId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGender(@PathVariable("id") int genderId) {
        genderService.deleteGender(genderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
