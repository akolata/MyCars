package pl.kolata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kolata.entity.Car;
import pl.kolata.entity.User;
import pl.kolata.repository.CarRepository;
import pl.kolata.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLConnection;

/**
 * Created by Aleksander on 2017-06-15.
 */
@Controller
public class PictureImageController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    @RequestMapping("/image")
    public void writePicture(HttpServletRequest request, HttpServletResponse response){
        try{
            User u = userRepository.findAll().get(0);

            response.getOutputStream().write(u.getProfileImage());
            response.setContentType("image/jpg");
        }catch(Exception e){

        }
    }

    @RequestMapping("/image/cars/car")
    public void writeCarImage(@RequestParam(name = "id") String id,
                              HttpServletRequest request,
                              HttpServletResponse response){
        try{
            Car car = carRepository.findOne(Long.parseLong(id));

            response.getOutputStream().write(car.getCarImage());
            response.setContentType("image/jpg");
        }catch(Exception e){

        }
    }
}
