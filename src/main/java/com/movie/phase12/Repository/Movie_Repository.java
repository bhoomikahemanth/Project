package com.movie.phase12.Repository;

import com.movie.phase12.module.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class Movie_Repository implements moviejdbc {

    private JdbcTemplate jdbc;
   //private CastRepository c;
  // private  actorjdbc a;
    @Autowired
  public Movie_Repository(JdbcTemplate jdbc)
  {
      this.jdbc = jdbc;
   //   this.c=c;
    //  this.a=a;
  }
//end::classShell[]

    //tag::finders[]
    public Iterable<Movie> findAll(String sort ) {
        String s= "SELECT * FROM tblMovies order by " + sort;
        return jdbc.query(s, this::mapRowToMovie);
    }

    @Override
    public Iterable<Movie> findAll() {
        String s= "SELECT * FROM tblMovies order by movie_id";
        return jdbc.query(s, this::mapRowToMovie);    }

    public Movie findByid(int id ) {

        return jdbc.queryForObject(
                "select * from tblMovies where movie_id=?",
                this::mapRowToMovie, id);
    }

    @Override
    public Iterable<String> Allgenre() {
      // Iterable<String> categories= (Iterable<String>) jdbc.query("SELECT distinct movie_genre FROM tblMovies ", );
        List<String> data = jdbc.query("SELECT distinct movie_genre FROM tblMovies ", new RowMapper<String>(){
            public String mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                return rs.getString("movie_genre");
            }
        });

return tostring(data);
    }

    public Iterable<String> tostring (List<String> data){
        String out="" ;
        for (String x: data) {
            out+=x+",";
        }

      out=  out.replaceAll("\\s","");
    data.clear();
       data.addAll(Arrays.asList(out.split(",")));
        Set<String> set = new HashSet<>(data);
        data.clear();
        data.addAll(set);
        return data;
    }

    @Override
    public Iterable<Movie> MoviesBygenre(String genre) {
        String q= "select * from tblMovies where movie_genre LIKE '%" + genre + "%'";
        return jdbc.query(
                q, this::mapRowToMovie);
    }


    public void add(Movie movie, Iterable<Integer> actro_list) {
            String sql = "insert  into tblMovies (movie_title, movie_summary, movie_duration, movie_genre, movie_release_date, movie_cover_image, movie_trailer) values(?,?,?,?,?,?,?)";

            jdbc.update(sql, new Object[]{movie.getMovie_title(), movie.getMovie_summery(), movie.getMovie_duration(), movie.getMovie_genre(), movie.getMovie_release_date(), movie.getMovie_cover_image(), movie.getMovie_trailer()});

        for (int actor: actro_list) {


        String Sql2= "insert into tblMovieCast (actor_id,movie_id) values ( ?,? )";
    jdbc.update(Sql2, new Object[]{movie.getMovie_id(), actor});


    }}

    @Override
    public void deletebyMovie_id(int Movie_id) {

        jdbc.update("delete  from tblMovies where movie_id=?;", Movie_id);
    }

    @Override
    public void updateMovie(Movie movie) {
        String sql = "update tblMovies set movie_title=?, movie_summary=?, movie_duration=?, movie_genre=?, movie_release_date=?, movie_cover_image=?, movie_trailer=?  Where movie_id=?";
        jdbc.update(sql,movie.getMovie_title(), movie.getMovie_summery(), movie.getMovie_duration(), movie.getMovie_genre(), movie.getMovie_release_date(), movie.getMovie_cover_image(), movie.getMovie_trailer(),
        movie.getMovie_id());

    }

   /* @Override
    public Iterable<Actor> findCast(int movie_id) {

        ArrayList<Actor> movie_cast= new ArrayList<>();
        for (Cast actor: c.findbymovie(movie_id)   ) {
          movie_cast.add(a.findBYid(actor.getActor_id()) );
        }
return  movie_cast;

    }
*/

    private Movie mapRowToMovie(ResultSet rs, int rowNum)
            throws SQLException {
        Movie m= new Movie();
        m.setMovie_id(rs.getInt("movie_id"));
        m.setMovie_title(rs.getString("movie_title"));
        m.setMovie_summery(rs.getString("movie_summary"));
        m.setMovie_duration(rs.getString("movie_duration"));
        m.setMovie_genre(rs.getString("movie_genre"));
        m.setMovie_release_date(rs.getString("movie_release_date"));
        m.setMovie_cover_image(rs.getString("movie_cover_image"));
        m.setMovie_trailer(rs.getString("movie_trailer"));
        m.setAvg_rate((int)rs.getInt("rate"));



        return  m;
    }
}
