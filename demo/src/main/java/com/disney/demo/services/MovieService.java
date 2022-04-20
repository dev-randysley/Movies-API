package com.disney.demo.services;

import com.disney.demo.models.entities.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getCharacters(String order);

    Movie getById(int peliculaId);

    Movie create(Movie pelicula);

    Movie update(Movie newMovie, int movieId);

    void delete(int peliculaId);

}
