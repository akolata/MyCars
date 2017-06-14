package pl.kolata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kolata.dto.ProfileForm;

import javax.validation.Valid;

/**
 * Created by Aleksander on 2017-06-14.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping
    public String showProfilePage(ProfileForm profileForm){
        return "profilePage";
    }

    @RequestMapping(method = RequestMethod.POST,params = {"save"})
    public String saveProfileData(@Valid ProfileForm profileForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "profilePage";
        }

        System.out.println(profileForm);
        return "redirect:/profile";
    }
}
