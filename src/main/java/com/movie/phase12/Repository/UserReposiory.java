package com.movie.phase12.Repository;


import com.movie.phase12.module.Login;
import com.movie.phase12.module.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserReposiory implements Userjdbc {
    @Autowired
   // private PasswordEncoder passwordEncoder;
    private JdbcTemplate jdbc;
private  reviewJdbc rev;

    @Autowired
    public UserReposiory(JdbcTemplate jdbc)
    {
        this.jdbc = jdbc;
        //  this.c= c;
        // this.m=m;
    }
    @Override
    public Iterable<User> findAll() {
        return jdbc.query("SELECT * FROM tblUsers", this::mapRowToUser);

    }

    @Override
    public User findByID(int id) {
        return  jdbc.queryForObject("SELECT * FROM tblUsers Where user_id=? ", this::mapRowToUser, id);
    }

  /*  @Override
    public String upatepass() {

        jdbc.update("update tblUsers set password = ? where user_id = ?", "pass", 1);
      //  String x= (String)passwordEncoder.encode("pass");
        return x;
    }*/

@Override
    public void register(User user) {

        String sql = "insert  into tblUsers (username, password, user_name, user_age, user_email, user_join_date) values(?,?,?,?,?,?)";

        jdbc.update(sql, new Object[] { user.getUsername(), user.getPassword(), user.getUser_name(),
                user.getUser_age(), user.getUser_name(), user.getUser_join_date() });
    }
@Override
    public User validateUser(Login login) {

        String sql = "select * from tblUsers where username='" + login.getUsername() + "' and password='" + login.getPassword()
                + "'";

        List<User> users = jdbc.query(sql, this::mapRowToUser);

        return users.size() > 0 ? users.get(0) : null;
    }


    private User mapRowToUser(ResultSet rs, int rowNum)
            throws SQLException {
        User user= new User();
        user.setUser_id(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setUser_name(rs.getString("user_name"));
        user.setUser_age(rs.getInt("user_age"));
        user.setUser_email(rs.getString("user_email"));
        user.setUser_join_date(rs.getString("user_join_date"));
        user.setUser_image(rs.getString("user_image"));
        user.setRole(rs.getString("role"));
        return  user;
    }


}
