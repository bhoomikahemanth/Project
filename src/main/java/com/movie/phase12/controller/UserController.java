package com.movie.phase12.controller;



import com.movie.phase12.Repository.Userjdbc;
import com.movie.phase12.Repository.moviejdbc;
import com.movie.phase12.Repository.reviewJdbc;
import com.movie.phase12.module.Login;
import com.movie.phase12.module.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    private final reviewJdbc jdbcreview;
    private  final Userjdbc userjdbc;
    private final moviejdbc jdbcRepos ;
    public  static User user;


    @Autowired
    public UserController(reviewJdbc jdbcreview, Userjdbc userjdbc, moviejdbc jdbcRepos) {
        this.jdbcreview = jdbcreview;
        this.userjdbc = userjdbc;
        this.jdbcRepos=jdbcRepos;
    }



    // get one user by id
    @GetMapping("/user")
    public String user_display(@RequestParam(name ="id" , required = false, defaultValue = "1")int id, Model model){

        model.addAttribute("user",userjdbc.findByID(id) );
        model.addAttribute("reviews", jdbcreview.findByuser(id));
        return "User_pro";
    }

// display a new user register page
    @GetMapping( "/register")
    public String showRegister(Model model) {
      //  ModelAndView mav = new ModelAndView("user/register");
        model.addAttribute("login", new Login());
        model.addAttribute("user", new User());

        return "user/register";
    }

    // apply registration process  to the data base
    @PostMapping( "/registerProcess")
    public ModelAndView addUser(@ModelAttribute("user") User user) {

        userjdbc.register(user);

        return new ModelAndView("index", "firstname", user.getUsername());
    }

    // login create new login object to take user login data
    @GetMapping( "/login")
    public String showLogin(Model model) {
        model.addAttribute("login", new Login());

        return "adminAllMovies";
    }
    // validate user data to decide how to redirect it
    // role-user will be redirected to the index page with user info
    // role admin will be redirected to the manageMovies page
    // worng user or pass will be redirected to the index page with a warning msg
    @PostMapping("/loginProcess")
    public String loginProcess(@ModelAttribute("login") Login login, Model model) {
       // ModelAndView mav = null;

        user = userjdbc.validateUser(login);

        if ( null!=user  ) {
            if(user.getRole().equals("admin")){
                return "redirect:/manageMovies";
            }
           return "redirect:/";
        } else {
            model.addAttribute("message", "Username or Password is wrong!!");
            return "redirect:/";
        }

    }
}
