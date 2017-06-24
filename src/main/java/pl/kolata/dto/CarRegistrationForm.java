package pl.kolata.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class used as a data transfer object while adding a new car to db
 * Created by Aleksander on 2017-06-24.
 */
public class CarRegistrationForm {

    @NotNull
    @Size(min = 3,max = 20)
    private String  brand;
    @NotNull
    @Size(min = 1,max = 20)
    private String  model;

    public CarRegistrationForm(){}

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "CarRegistrationForm{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
