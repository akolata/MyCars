package pl.kolata.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Aleksander on 2017-06-15.
 */
@Entity
@Table(name = "CAR")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "MILEAGE",precision = 0)
    private Float mileage;
    @Column(name = "FUEL_TYPE")
    @Enumerated(value = EnumType.STRING)
    private FuelType fuelType;
    @Embedded
    private CarDetails carDetails;
    @Lob
    private byte[] carImage;

    public Car(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Float getMileage() {
        return mileage;
    }

    public void setMileage(Float mileage) {
        this.mileage = mileage;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public CarDetails getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(CarDetails carDetails) {
        this.carDetails = carDetails;
    }

    public byte[] getCarImage() {
        return carImage;
    }

    public void setCarImage(byte[] carImage) {
        this.carImage = carImage;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                ", fuelType=" + fuelType +
                ", carDetails=" + carDetails +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
