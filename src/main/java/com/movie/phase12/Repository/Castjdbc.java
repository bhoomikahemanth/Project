package com.movie.phase12.Repository;


import com.movie.phase12.module.Actor;
import com.movie.phase12.module.Cast;
import com.movie.phase12.module.Movie;

public interface Castjdbc {

    Iterable<Cast> findbymovie(int movie_id);
    Iterable<Cast> findbyactor(int actor_id);
    Iterable<Movie> findmovies(int id);
    Iterable<Actor> findCast(int movie_id);
}
