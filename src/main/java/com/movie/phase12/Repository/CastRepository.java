package com.movie.phase12.Repository;


import com.movie.phase12.module.Actor;
import com.movie.phase12.module.Cast;
import com.movie.phase12.module.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class CastRepository  implements Castjdbc{



    private JdbcTemplate jdbc;
    private  actorjdbc a;
    private moviejdbc m;

    @Autowired
    public CastRepository(JdbcTemplate jdbc ,  actorjdbc a, moviejdbc m ) {
        this.jdbc = jdbc;
        this.a=a;
        this.m=m ;
    }

    @Override
    public Iterable<Cast> findbymovie(int movie_id) {
        return jdbc.query("SELECT * FROM tblMovieCast where movie_id=?", this::mapRowTocast, movie_id);

    }

    @Override
    public Iterable<Cast> findbyactor(int actor_id) {
        return jdbc.query("SELECT * FROM tblMovieCast where actor_id=?", this::mapRowTocast, actor_id);
    }


    public Iterable<Movie> findmovies(int actor_id) {

        ArrayList<Movie> movie_list= new ArrayList<>();
        for (Cast movie: findbyactor(actor_id)) {
            movie_list.add(m.findByid(movie.getMovie_id()) );
        }
        return  movie_list;

    }
    public Iterable<Actor> findCast(int movie_id) {

        ArrayList<Actor> movie_cast= new ArrayList<>();
        for (Cast actor: findbymovie(movie_id)   ) {
            movie_cast.add(a.findBYid(actor.getActor_id()) );
        }
        return  movie_cast;

    }




    private Cast mapRowTocast(ResultSet rs, int rowNum)
            throws SQLException {
        Cast cast1= new Cast();
        cast1.setMovie_cast_id(rs.getInt("movie_cast_id"));
        cast1.setMovie_id(rs.getInt("movie_id"));
        cast1.setActor_id(rs.getInt("actor_id"));
        return  cast1;
    }
}
