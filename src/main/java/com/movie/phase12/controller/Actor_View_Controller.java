package com.movie.phase12.controller;



import com.movie.phase12.Repository.Castjdbc;
import com.movie.phase12.Repository.actorjdbc;
import com.movie.phase12.module.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Actor_View_Controller {

    private final actorjdbc actorsview;
    private final Castjdbc castjdbc;

    @Autowired
    public Actor_View_Controller(actorjdbc actorsview, Castjdbc castjdbc) {
        this.actorsview = actorsview;
        this.castjdbc = castjdbc;
    }
// view all actors for any user
    @GetMapping("/viewActors")
    public String Actor_displayall(Model model, @RequestParam(name ="sort" , required = false, defaultValue = "actor_id") String sort){
        model.addAttribute("all_actor",actorsview.findAll(sort) );
     //   return "user_views/Allactors";
        model.addAttribute("login", new Login());
        if(null!=UserController.user)
            model.addAttribute("user", UserController.user);
        return "james_view/viewActors";

    }
// view on actor profile by id for any user
    @GetMapping("/viewActor")
    public String Actor_display(Model model, @RequestParam(name ="id" , required = false, defaultValue = "1")int id){
        model.addAttribute("actor",actorsview.findBYid(id) );
        model.addAttribute("movies",castjdbc.findmovies(id));
       // return "user_views/Actor_profile";
        model.addAttribute("login", new Login());
        if(null!=UserController.user)
            model.addAttribute("user", UserController.user);
        return "james_view/saberact";

    }
}
