package com.movie.phase12.Repository;

import com.movie.phase12.module.Movie;

public interface moviejdbc {
    Iterable<Movie> findAll(String sort);
    Iterable<Movie> findAll();
    Movie findByid(int id);
    Iterable<String> Allgenre();
    Iterable<Movie> MoviesBygenre(String genre);
    void add (Movie movie, Iterable<Integer> actors);
    void deletebyMovie_id(int Movie_id);
    void updateMovie(Movie M);

}
