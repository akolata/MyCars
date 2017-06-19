package pl.kolata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kolata.entity.Car;

/**
 * Created by Aleksander on 2017-06-15.
 */
public interface CarRepository
extends JpaRepository<Car,Long>{
}
