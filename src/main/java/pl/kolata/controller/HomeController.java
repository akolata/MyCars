package pl.kolata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller used to display application home page under /home url
 * Created by Aleksander on 2017-06-12.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

    /**
     * Displays home page of application
     * @return application home page
     */
    @GetMapping
    public String showHomePage(){
        return "homePage";
    }
}
