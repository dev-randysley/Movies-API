package com.disney.demo;

import com.disney.demo.models.entities.Character;
import com.disney.demo.models.entities.Gender;
import com.disney.demo.models.entities.Movie;
import com.disney.demo.repositorys.CharacterRepository;
import com.disney.demo.repositorys.GenderRepository;
import com.disney.demo.repositorys.MovieRepository;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class DisneyWorldApplication implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DisneyWorldApplication.class);

    @Autowired
    private Faker faker;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private GenderRepository genderRepository;

    public static void main(String[] args) {
        SpringApplication.run(DisneyWorldApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Movie> moviesListOne = new ArrayList<>();
        List<Movie> moviesListTwo = new ArrayList<>();
        List<Movie> moviesListThree = new ArrayList<>();

        loadMovies(faker, moviesListOne, movieRepository);
        loadMovies(faker, moviesListTwo, movieRepository);
        loadMovies(faker, moviesListThree, movieRepository);

        loadCharacter(faker, moviesListOne, characterRepository);
        loadCharacter(faker, moviesListTwo, characterRepository);
        loadCharacter(faker, moviesListThree, characterRepository);

        loadGender("terror", moviesListOne, genderRepository);
        loadGender("action", moviesListTwo, genderRepository);
        loadGender("fantasy", moviesListThree, genderRepository);


    }

    private static void loadGender(String genderName, List<Movie> movies, GenderRepository genderRepository) {
        Gender gender = new Gender();
        gender.setName(genderName);
        gender.setMovies(movies);
        genderRepository.save(gender);
    }

    private static void loadMovies(Faker faker, List<Movie> movies, MovieRepository movieRepository) {
        for (int i = 0; i < 10; i++) {
            Movie movie = new Movie();
            movie.setQualification((int) Math.round(Math.random() * 4 + 1));
            movie.setTitle(faker.animal().name());
            movieRepository.save(movie);
            movies.add(movie);
        }
    }

    private static void loadCharacter(Faker faker, List<Movie> movies, CharacterRepository characterRepository) {
        Character character = new Character();
        character.setAge((int) Math.round(Math.random() * 20 + 1));
        character.setHistoria(faker.hobbit().character());
        character.setName(faker.funnyName().name());
        character.setPeso((int) Math.round(Math.random() * 80 + 1));
        character.setPeliculas(movies);
        characterRepository.save(character);

    }
}


