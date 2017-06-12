package pl.kolata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Aleksander on 2017-06-12.
 */
@Controller
@RequestMapping(value = "/Home")
public class HomeController {

    @GetMapping
    public String showHomePage(){
        return "homePage";
    }
}
