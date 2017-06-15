package pl.kolata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.kolata.dto.ProfileForm;
import pl.kolata.entity.User;
import pl.kolata.repository.UserRepository;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Aleksander on 2017-06-14.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private UserRepository userRepository;
    private  MessageSource messageSource;

    @Autowired
    public ProfileController(UserRepository userRepository,MessageSource messageSource){
        this.userRepository = userRepository;
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

    @GetMapping(value = "/{id}")
    public String showProfilePage(
            @PathVariable(value = "id",required = true) Long id, Model model){
        User user = userRepository.findAll().get(0);
        model.addAttribute("profileForm",ProfileForm.createProfileFormFromUser(user));
        model.addAttribute("image",user.getProfileImage() != null);
        return "profilePage";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.POST,params = {"load"})
    public String onPictureLoad(MultipartFile file, RedirectAttributes redirectAttributes,Locale locale) throws IOException {

        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("error",messageSource.getMessage("image.file.empty",null,locale));
            return "redirect:/profile/1";
        }else if (!file.getContentType().startsWith("image")){
            redirectAttributes.addFlashAttribute("error",messageSource.getMessage("image.file.invalid",null,locale));
            return "redirect:/profile/1";
        }

        User user = userRepository.findAll().get(0);
        user.setProfileImage(file.getBytes());
        userRepository.saveAndFlush(user);

        return  "redirect:/profile/1";
    }

    @RequestMapping(method = RequestMethod.POST,params = {"save"})
    public String saveProfileData(@Valid ProfileForm profileForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "profilePage";
        }

        User user = userRepository.findAll().get(0);
        user.updateProfileFromProfileForm(profileForm);
        userRepository.saveAndFlush(user);

        return "redirect:/profile/" + user.getId();
    }
}
