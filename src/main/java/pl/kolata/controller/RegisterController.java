package pl.kolata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kolata.dto.ProfileForm;
import pl.kolata.entity.User;
import pl.kolata.repository.UserRepository;
import pl.kolata.service.UserService;
import javax.validation.Valid;

/**
 * Controller used with registration page
 * Created by Aleksander on 2017-06-24.
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    private static final String REGISTRATION_PAGE_NAME = "register",
                                LOGIN_PAGE_NAME = "login";
    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public RegisterController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /**
     * Called when user wants to register
     * @param model View model
     * @return registration page name
     */
    @GetMapping
    public String showRegistrationPage(Model model) {

        model.addAttribute("profileForm",new ProfileForm());
        return REGISTRATION_PAGE_NAME;
    }

    /**
     * Called when user submits registration form
     * @param profileForm new user form
     * @param bindingResult form's validation result
     * @param model View model
     * @return registration page name
     */
    @PostMapping
    public String submitRegistrationForm(@Valid ProfileForm profileForm,
                                         BindingResult bindingResult,
                                         Model model){

        if(bindingResult.hasErrors()){
            return REGISTRATION_PAGE_NAME;
        }

        if(userRepository.findByLogin(profileForm.getLogin()) != null){
            model.addAttribute("error",true);
            return REGISTRATION_PAGE_NAME;
        }else{
            model.addAttribute("error",false);
        }

        registerUserInDb(profileForm);

        return LOGIN_PAGE_NAME;
    }

    /**
     * Method used to register a new user in db
     * @param profileForm form from registration page
     */
    private void registerUserInDb(ProfileForm profileForm){
        User user = new User();
        user.updateProfileFromProfileForm(profileForm);

        userService.saveAndFlush(user);
    }
}
