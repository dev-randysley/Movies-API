package com.disney.demo.services.impl;

import com.disney.demo.models.entities.Movie;
import com.disney.demo.repositorys.MovieRepository;
import com.disney.demo.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<Movie> getCharacters(String order) {
        if (!order.isBlank()) {
            if (order.equals("ASC"))
                return movieRepository.findAllByOrderByStartDateAsc();
            return movieRepository.findAllByOrderByStartDateDesc();
        }
        return movieRepository.findAll();
    }

    @Override
    public Movie getById(int movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("movieId %d doesn´t exists", movieId)));
    }

    @Override
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Movie newMovie, int movieId) {
        movieRepository.findById(movieId).ifPresentOrElse(
                movie -> {
                    movie.setQualification(newMovie.getQualification());
                    movie.setStartDate(newMovie.getStartDate());
                    movie.setTitle(newMovie.getTitle());
                    movieRepository.save(movie);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("movieId %d doesn´t exists", movieId));
                }
        );
        return newMovie;
    }

    @Override
    public void delete(int movieId) {
        movieRepository.findById(movieId).ifPresentOrElse(
                movie -> {
                    movieRepository.delete(movie);
                },
                () -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            String.format("Movieid %d doesn´t exists", movieId));
                }
        );
    }
}
