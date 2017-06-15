package pl.kolata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kolata.entity.User;
import pl.kolata.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Aleksander on 2017-06-15.
 */
@Controller
public class PictureImageController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/image")
    public void writePicture(HttpServletRequest request, HttpServletResponse response){
        try{
            User u = userRepository.findAll().get(0);
            byte[] image = u.getProfileImage();


            response.getOutputStream().write(u.getProfileImage());
            response.setContentType("image/jpg");
        }catch(Exception e){

        }
    }
}
