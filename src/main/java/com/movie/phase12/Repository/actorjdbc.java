package com.movie.phase12.Repository;

import com.movie.phase12.module.Actor;

public interface actorjdbc {
    Iterable<Actor> findAll(String sort);

    Iterable<Actor> findBygender(String gender);
    Iterable<Actor> findByname(String actor_name);
    Actor findBYid(int id);
    void add(Actor actor);
}
