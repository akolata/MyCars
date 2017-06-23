package pl.kolata.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kolata.entity.*;
import pl.kolata.repository.AuthorityRepository;
import pl.kolata.repository.CarRepository;
import pl.kolata.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aleksander on 2017-06-15.
 */
@Component
public class RunAtStart {

    private UserRepository userRepository;
    private CarRepository carRepository;
    private AuthorityRepository authorityRepository;

    @Autowired
    public RunAtStart(UserRepository userRepository, CarRepository carRepository, AuthorityRepository authorityRepository){
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.authorityRepository = authorityRepository;
    }

    @PostConstruct
    public void initialize(){
        User user = generateSampleUser();
        Authority authority = new Authority("USER");
        Car car1 = generateSampleCar();
        Car car2 = generateSampleCar2();

        userRepository.saveAndFlush(user);
        authorityRepository.saveAndFlush(authority);
        carRepository.saveAndFlush(car1);
        carRepository.saveAndFlush(car2);

        Set<Car> cars = new HashSet<>();
        cars.add(car1);
        cars.add(car2);

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);

        user.setCars(cars);
        user.setAuthorities(authorities);

        userRepository.saveAndFlush(user);
    }

    private User generateSampleUser(){
        User user = new User();
        user.setLogin("login");
        user.setPassword(new BCryptPasswordEncoder().encode("password"));
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPhone("111-111-111");
        user.setEmail("sample@mail.com");
        return user;
    }

    private Car generateSampleCar(){
        Car car = new Car();
        car.setBrand("Ford");
        car.setModel("Mondeo");
        car.setMileage(35000f);
        car.setFuelType(FuelType.DIESEL);

        CarDetails carDetails = new CarDetails();
        carDetails.setAverageConsumption(6.5f);
        carDetails.setCityConsumption(8.0f);
        carDetails.setHighwayConsumption(6.0f);
        carDetails.setEngineSize(1999f);
        carDetails.setHorsePower(180l);
        carDetails.setInsuranceDate(LocalDate.now());
        carDetails.setServiceDate(LocalDate.now());
        carDetails.setYearlyDistance(25000l);

        car.setCarDetails(carDetails);
        return car;
    }

    private Car generateSampleCar2(){
        Car car = new Car();
        car.setBrand("Skoda");
        car.setModel("Octavia");
        car.setMileage(29000f);
        car.setFuelType(FuelType.PETROL);

        CarDetails carDetails = new CarDetails();
        carDetails.setAverageConsumption(7.0f);
        carDetails.setCityConsumption(8.0f);
        carDetails.setHighwayConsumption(7.0f);
        carDetails.setEngineSize(1400f);
        carDetails.setHorsePower(140l);
        carDetails.setInsuranceDate(LocalDate.now());
        carDetails.setServiceDate(LocalDate.now());
        carDetails.setYearlyDistance(5000l);

        car.setCarDetails(carDetails);
        return car;
    }
}
