package pl.kolata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Aleksander on 2017-06-15.
 */
@Controller
public class CarDetailController {

    @GetMapping(value = "/car")
    public String showCarDetails(){
        return "carPage";
    }


}
