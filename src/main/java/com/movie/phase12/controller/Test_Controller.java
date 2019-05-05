package com.movie.phase12.controller;

import com.movie.phase12.Repository.*;
import com.movie.phase12.module.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test_Controller {

    private final moviejdbc jdbcRepos ;
    private final reviewJdbc jdbcreview;
    private final actorjdbc actorsview;
    private final Castjdbc castjdbc;
    private  final Userjdbc userjdbc;
    private Movie Movie_to_edit=null;
    @Autowired
    public Test_Controller(Userjdbc userjdbc , moviejdbc jdbcRepos, Castjdbc castjdbc , reviewJdbc jdbcreview, actorjdbc actorsview)
    {
        this.jdbcRepos= jdbcRepos;
        this.jdbcreview= jdbcreview;
        this.actorsview= actorsview;
        this.castjdbc=castjdbc;
        this.userjdbc= userjdbc;
    }


    // view all users as a test for the developping sake

    @GetMapping("/Allmovies")
    public String movie_displayAll(Model model){
        model.addAttribute("users",jdbcRepos.findAll() );
        return "AllUser";
    }

    // view all reviews as a test for the developping sake
    @GetMapping("/viewReviews")
    public String displayReviews (Model model){


        model.addAttribute("reviews", jdbcreview.findAll());

        return "test_page/viewReviews";
    }

    // view all users as a test for the developping sake

    @GetMapping("/Allusers")
    public String user_displayAll(Model model){
        model.addAttribute("users",userjdbc.findAll() );
        return "AllUser";
    }
}
