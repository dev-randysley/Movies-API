package com.disney.demo.repositorys;

import com.disney.demo.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findAllByOrderByStartDateAsc();

    List<Movie> findAllByOrderByStartDateDesc();
}
