package pl.kolata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Aleksander on 2017-06-14.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping
    public String showProfilePage(){
        return "profilePage";
    }
}
