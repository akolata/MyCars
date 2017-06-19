package pl.kolata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.kolata.dto.ProfileForm;
import pl.kolata.entity.User;
import pl.kolata.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Aleksander on 2017-06-14.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private User user;
    private UserService userService;
    private MessageSource messageSource;

    @Autowired
    public ProfileController(UserService userService, MessageSource messageSource){
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(Locale locale){
        ModelAndView modelAndView = new ModelAndView("/profile/1");
        modelAndView.addObject("error",messageSource.getMessage("image.io.exception",null,locale));
        return modelAndView;
    }

    @RequestMapping("/uploadError")
    public ModelAndView handleUploadError(Locale locale){
        ModelAndView modelAndView = new ModelAndView("/profile/1");
        modelAndView.addObject("error",messageSource.getMessage("image.file.too.big",null,locale));
        return modelAndView;
    }

    @GetMapping
    public String showProfilePage(Model model){
        user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("profileForm",ProfileForm.createProfileFormFromUser(user));
        model.addAttribute("image",user.getProfileImage() != null);

        return "profilePage";
    }

    @RequestMapping(method = RequestMethod.POST,params = {"load"})
    public String onPictureLoad(MultipartFile file, RedirectAttributes redirectAttributes,Locale locale) throws IOException {

        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("error",messageSource.getMessage("image.file.empty",null,locale));
            return "redirect:/profile";
        }else if (!file.getContentType().startsWith("image")){
            redirectAttributes.addFlashAttribute("error",messageSource.getMessage("image.file.invalid",null,locale));
            return "redirect:/profile";
        }

        user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setProfileImage(file.getBytes());
        userService.saveAndFlush(user);

        return  "redirect:/profile";
    }

    @RequestMapping(method = RequestMethod.POST,params = {"save"})
    public String saveProfileData(@Valid ProfileForm profileForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "profilePage";
        }

        user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.updateProfileFromProfileForm(profileForm);
        userService.saveAndFlush(user);

        return "redirect:/profile";
    }

    @GetMapping(value = "/cars")
    public String showUsersCars(Model model){

        user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("cars",user.getCars());

        return "carsPage";
    }
}
