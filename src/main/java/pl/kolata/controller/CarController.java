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
import pl.kolata.dto.CarRegistrationForm;
import pl.kolata.entity.Car;
import pl.kolata.entity.FuelType;
import pl.kolata.entity.User;
import pl.kolata.repository.CarRepository;
import pl.kolata.repository.UserRepository;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;
import java.util.Set;

/**
 * Controller used to manage pages connected with cars under profile/cars/... url
 * Created by Aleksander on 2017-06-15.
 */
@Controller
@RequestMapping("/profile/cars")
public class CarController {

    private static final String REDIRECT_TO_CAR_PAGE_URL = "redirect:/profile/cars/car/%s",
                                USERS_CARS_PAGE_NAME = "cars",
                                CAR_DETAILS_PAGE_NAME = "car";
    private CarRepository carRepository;
    private MessageSource messageSource;
    private UserRepository userRepository;

    @Autowired
    public CarController(CarRepository carRepository, MessageSource messageSource, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.messageSource = messageSource;
        this.userRepository = userRepository;
    }

    /**
     * Called when IOException occurs
     * @param locale current Locale
     * @return cars page model
     */
    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(Locale locale){

        ModelAndView modelAndView = new ModelAndView("/profile/cars");
        modelAndView.addObject("error",messageSource.getMessage("image.io.exception",null,locale));

        return modelAndView;
    }

    /**
     * Called if any error connected with image file will occure
     * @param locale current Locale
     * @return cars page model
     */
    @RequestMapping("/uploadError")
    public ModelAndView handleUploadError(Locale locale){

        ModelAndView modelAndView = new ModelAndView("/profile/cars");
        modelAndView.addObject("error",messageSource.getMessage("image.file.too.big",null,locale));

        return modelAndView;
    }

    /**
     * Method is getting user's cars from db and adding them to the model
     * @param model page model
     * @return page with user's cars
     */
    @GetMapping
    public String showUsersCars(Model model){

        model.addAttribute("carRegistrationForm",new CarRegistrationForm());
        return USERS_CARS_PAGE_NAME;
    }

    /**
     * Method called when user adds new car to db
     * @param carRegistrationForm car's brand and model
     * @param bindingResult forms validation result
     * @param model View model
     * @return page with users cars
     */
    @PostMapping(value = "/car/add")
    public String addCarToDb(@Valid CarRegistrationForm carRegistrationForm,
                             BindingResult bindingResult,
                             Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("error",true);
            return USERS_CARS_PAGE_NAME;
        }

        model.addAttribute("error",false);
        addCarToDb(carRegistrationForm);

        return "redirect:/profile/cars";
    }

    /**
     * Shows current car details page
     * @param id current car
     * @param model page model
     * @return car edition page
     */
    @GetMapping(value = "/car/{id}")
    public String showCarDetails(@PathVariable(name = "id") String id,
                                 Model model) {

        Car car = carRepository.findOne(Long.valueOf(id));
        model.addAttribute("car",car);
        model.addAttribute("hasImage",car.getCarImage() != null);

        return CAR_DETAILS_PAGE_NAME;
    }


    /**
     * Called when user will submit form and choose which image to load into db as car image
     * @param id currently edited car id
     * @param file file chose by user
     * @param redirectAttributes
     * @param locale current Locale
     * @return car edition page
     * @throws IOException if something gone wrong while reading bytes from file
     */
    @PostMapping(value = "/car/{id}",params = {"load"})
    public String loadCarPicture(@PathVariable(name = "id") String id,
                                 MultipartFile file,
                                 RedirectAttributes redirectAttributes, Locale locale) throws IOException {

        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("error",messageSource.getMessage("image.file.empty",null,locale));
            return String.format(REDIRECT_TO_CAR_PAGE_URL,id);
        }else if (!file.getContentType().startsWith("image")){
            redirectAttributes.addFlashAttribute("error",messageSource.getMessage("image.file.invalid",null,locale));
            return String.format(REDIRECT_TO_CAR_PAGE_URL,id);
        }

        Car car = carRepository.findOne(Long.valueOf(id));
        car.setCarImage(file.getBytes());

        carRepository.saveAndFlush(car);

        return String.format(REDIRECT_TO_CAR_PAGE_URL,id);
    }

    /**
     * Called when user will submit the form and edit car data
     * @param id currently edited car id
     * @param car car data from form
     * @param bindingResult form results
     * @return car edition page
     */
    @PostMapping(value = "/car/{id}",params = {"save"})
    public String saveCarDetails(@PathVariable(name = "id") String id,
                                 @Valid Car car,
                                 BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return String.format(REDIRECT_TO_CAR_PAGE_URL,id);
        }

        car.setCarImage(carRepository.findOne(Long.valueOf(car.getId())).getCarImage());
        carRepository.saveAndFlush(car);

        return String.format(REDIRECT_TO_CAR_PAGE_URL,id);
    }

    /**
     * Called when user will choose to delete car from db
     * @param id currently edited car id
     * @return page with all cars
     */
    @PostMapping(value = "/car/{id}",params = {"delete"})
    public String deleteCar(@PathVariable(name = "id") String id){

        carRepository.delete(Long.valueOf(id));
        carRepository.flush();

        return "redirect:/profile/cars";
    }

    @PostMapping(value = "/car/{id}",params = {"history"})
    public String showCarHistory(@PathVariable(name = "id") String id){
        return "redirect:/profile/cars/car/" + id + "/history";
    }

    @PostMapping(value = "/car/{id}",params = {"statistics"})
    public String showCarStatistics(@PathVariable(name = "id") String id){
        return "redirect:/profile/cars/car/" + id + "/statistics";
    }

    @ModelAttribute(name = "fuelTypes")
    public FuelType[] fuelTypes(){
        return FuelType.values();
    }

    @ModelAttribute(name = "cars")
    public Set<Car> userCars(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userRepository.findByLogin(user.getLogin());

        return userInDb.getCars();
    }

    /**
     * Method used to save a new car in db and assing it to user
     * @param carRegistrationForm new's car brand and model
     */
    private void addCarToDb(@Valid CarRegistrationForm carRegistrationForm) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userRepository.findByLogin(user.getLogin());

        Car car = new Car();
        car.setBrand(carRegistrationForm.getBrand());
        car.setModel(carRegistrationForm.getModel());
        carRepository.saveAndFlush(car);

        userInDb.getCars().add(car);
        userRepository.saveAndFlush(userInDb);
    }

}
