package pl.kolata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Aleksander on 2017-06-12.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String showLoginPage(){
        return "loginPage";
    }

    @PostMapping
    public String validateLogin(){
        return "redirect:/home";
    }
}
