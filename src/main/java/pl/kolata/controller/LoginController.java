package pl.kolata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller used to display login page
 * Created by Aleksander on 2017-06-12.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static final String LOGIN_PAGE_NAME = "login";

    @GetMapping
    public String showLoginPage(){
        return LOGIN_PAGE_NAME;
    }

    @PostMapping
    public String validateLogin(){
        return "redirect:/home";
    }
}
