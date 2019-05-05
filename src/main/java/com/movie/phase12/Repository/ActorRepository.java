package com.movie.phase12.Repository;

import com.movie.phase12.module.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ActorRepository implements actorjdbc {


    private JdbcTemplate jdbc;


    @Autowired
    public ActorRepository(JdbcTemplate jdbc)
    {
        this.jdbc = jdbc;
      //  this.c= c;
       // this.m=m;
    }

    @Override
    public Iterable<Actor> findAll(String sort)
    {
String sql= "SELECT * FROM tblActors order by "+sort;
        return jdbc.query(sql, this::mapRowToActor);
    }

    @Override
    public Iterable<Actor> findBygender(String gender) {
        return jdbc.query("SELECT * FROM tblActors where actor_gender=?", this::mapRowToActor, gender);

    }

    @Override
    public Iterable<Actor> findByname(String name) {
        return jdbc.query("SELECT * FROM tblActors where actor_name LIKE '%?%' ", this::mapRowToActor, name);
    }

    @Override
   public  Actor findBYid(int id){
        return jdbc.queryForObject(
                "select * from tblActors where actor_id=?",
                this::mapRowToActor, id); }

    @Override
    public void add(Actor actor) {
        String sql = "insert  into tblActors (actor_name, actor_age, actor_gender, actor_profile_image) values(?,?,?,?,?)";

        jdbc.update(sql, new Object[]{actor.getActor_name(),actor.getActor_age(), actor.getActor_gender(),actor.getActor_profile_image()});
    }



 /*  @Override
    public Iterable<movies> findmovies(int actor_id) {

            ArrayList<movies> movie_list= new ArrayList<>();
            for (Cast movie: c.findbyactor(actor_id)) {
                movie_list.add(m.findByid(movie.getMovie_id()) );
            }
            return  movie_list;

        }
*/

    private Actor mapRowToActor(ResultSet rs, int rowNum)
            throws SQLException {
        Actor actor= new Actor();
        actor.setActor_id(rs.getInt("actor_id"));
        actor.setActor_name(rs.getString("actor_name"));
        actor.setActor_age(rs.getInt("actor_age"));
        actor.setActor_gender(rs.getString("actor_gender"));
        actor.setActor_profile_image(rs.getString("actor_profile_image"));



        return  actor;
    }

}
