package pl.kolata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kolata.entity.Car;
import pl.kolata.entity.User;
import pl.kolata.repository.CarRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller used to return images as bytes under given url, not assigned to any specific page
 * Created by Aleksander on 2017-06-15.
 */
@Controller
public class ImagesController {

    private CarRepository carRepository;

    @Autowired
    public ImagesController(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    /**
     * Called on user's profile page, return user's profile image
     * @param request request
     * @param response response
     */
    @RequestMapping("/image")
    public void writePicture(HttpServletRequest request, HttpServletResponse response){
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            response.getOutputStream().write(user.getProfileImage());
            response.setContentType("image/jpg");
        }catch(Exception e){}
    }

    /**
     * Called from car details page, return car's image
     * @param id car id
     * @param request request
     * @param response response
     */
    @RequestMapping("/image/cars/car")
    public void writeCarImage(@RequestParam(name = "id") String id,
                              HttpServletRequest request,
                              HttpServletResponse response){
        try{
            Car car = carRepository.findOne(Long.parseLong(id));

            response.getOutputStream().write(car.getCarImage());
            response.setContentType("image/jpg");
        }catch(Exception e){}
    }
}
