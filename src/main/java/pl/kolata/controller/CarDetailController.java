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
import pl.kolata.entity.Car;
import pl.kolata.entity.FuelType;
import pl.kolata.repository.CarRepository;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Aleksander on 2017-06-15.
 */
@Controller
@RequestMapping("/profile/cars")
public class CarDetailController {

    private static final String REDIRECT_TO_CAR_PAGE = "redirect:/profile/cars/car?id=%s";
    private CarRepository carRepository;
    private MessageSource messageSource;

    @Autowired
    public CarDetailController(CarRepository carRepository, MessageSource messageSource) {
        this.carRepository = carRepository;
        this.messageSource = messageSource;
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(Locale locale){
        ModelAndView modelAndView = new ModelAndView("/profile/cars");
        modelAndView.addObject("error",messageSource.getMessage("image.io.exception",null,locale));
        return modelAndView;
    }

    @RequestMapping("/uploadError")
    public ModelAndView handleUploadError(Locale locale){
        ModelAndView modelAndView = new ModelAndView("/profile/cars");
        modelAndView.addObject("error",messageSource.getMessage("image.file.too.big",null,locale));
        return modelAndView;
    }

    @GetMapping(value = "/car")
    public String showCarDetails(@RequestParam(name = "id") Long id,
                                 Model model) {

        Car car = carRepository.findOne(id);
        model.addAttribute("car",car);
        model.addAttribute("image",car.getCarImage() != null);

        return "carPage";
    }


    @PostMapping(value = "/car",params = {"load"})
    public String onPictureLoad(@RequestParam(name = "id") String id,
                                MultipartFile file,
                                RedirectAttributes redirectAttributes, Locale locale) throws IOException {

        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("error",messageSource.getMessage("image.file.empty",null,locale));
            return String.format(REDIRECT_TO_CAR_PAGE,id);
        }else if (!file.getContentType().startsWith("image")){
            redirectAttributes.addFlashAttribute("error",messageSource.getMessage("image.file.invalid",null,locale));
            return String.format(REDIRECT_TO_CAR_PAGE,id);
        }

        Car car = carRepository.findOne(Long.valueOf(id));
        car.setCarImage(file.getBytes());
        carRepository.saveAndFlush(car);

        return String.format(REDIRECT_TO_CAR_PAGE,id);
    }

    @PostMapping(value = "/car",params = {"save"})
    public String carDetailsEdited(@RequestParam(name = "id") String id,
                                   @Valid Car car,
                                   BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return String.format(REDIRECT_TO_CAR_PAGE,id);
        }

        car.setCarImage(carRepository.findOne(Long.valueOf(car.getId())).getCarImage());
        carRepository.saveAndFlush(car);

        return String.format(REDIRECT_TO_CAR_PAGE,id);
    }

    @PostMapping(value = "/car",params = {"delete"})
    public String deleteCar(@RequestParam(name = "id") String id){

        carRepository.delete(Long.valueOf(id));
        carRepository.flush();

        return "redirect:/profile/cars";
    }

    @ModelAttribute(name = "fuelTypes")
    public FuelType[] fuelTypes(){
        return FuelType.values();
    }

}
