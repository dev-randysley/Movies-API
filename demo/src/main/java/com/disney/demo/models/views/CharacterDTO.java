package com.disney.demo.models.views;

import com.disney.demo.models.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterDTO {
    private String name;
    private int age;
    private List<Movie> movies;
}
