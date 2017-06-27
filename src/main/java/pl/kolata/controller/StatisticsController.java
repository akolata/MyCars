package pl.kolata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kolata.entity.Car;
import pl.kolata.repository.CarRepository;
import pl.kolata.repository.NoteRepository;

/**
 * Controller used with statistics page
 * Created by Aleksander on 2017-06-24.
 */
@Controller
@RequestMapping(value = "/profile/cars/car")
public class StatisticsController {

    private static final String STATISTICS_PAGE_NAME = "statistics";
    private CarRepository carRepository;
    private NoteRepository noteRepository;
    private Car car;
    private Float   serviceCost,
                    partsCost,
                    distanceCost,
                    totalCost;

    @Autowired
    public StatisticsController(CarRepository carRepository, NoteRepository noteRepository){
        this.carRepository = carRepository;
        this.noteRepository = noteRepository;
    }

    /**
     * Response for GET request, method passes calculated statistics to model
     * @param id current car id
     * @param cost fuel cost from user
     * @param model view model
     * @return
     */
    @GetMapping(value = "/{id}/statistics")
    public String showCarStatisticsPage(@PathVariable(name = "id") String id,
                                        @RequestParam(name = "cost",defaultValue = "1") Float cost,
                                        Model model){

        car = carRepository.findOne(Long.valueOf(id));
        calculateStatisticsForOneCar(car,cost);

        model.addAttribute("id",id);
        model.addAttribute("carPrice",car.getCarDetails().getPrice());
        model.addAttribute("serviceCost",serviceCost);
        model.addAttribute("partsCost",partsCost);
        model.addAttribute("totalCost",totalCost);
        model.addAttribute("distance",car.getMileage());
        model.addAttribute("distanceCost",distanceCost);

        return STATISTICS_PAGE_NAME;
    }

    /**
     * Method responsible for calcuating statistics for given car
     * @param car car which statistics will be calculated
     * @param fuelCost cost in PLN from user
     */
    private void calculateStatisticsForOneCar(Car car, Float fuelCost) {
        serviceCost = noteRepository.sumCarServiceCost(car.getId());
        partsCost = noteRepository.sumCarPartsCost(car.getId());
        distanceCost = ((car.getMileage() / 100) * fuelCost * car.getCarDetails().getAverageConsumption());
        totalCost = distanceCost + serviceCost + partsCost + car.getCarDetails().getPrice();
    }
}
