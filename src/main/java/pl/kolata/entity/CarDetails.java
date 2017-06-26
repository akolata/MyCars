package pl.kolata.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

/**
 * Class used with Car entity - used to separate car's details
 * Created by Aleksander on 2017-06-15.
 */
@Embeddable
public class CarDetails {

    @Column(name = "INSURANCE_DATE")
    private LocalDate insuranceDate;
    @Column(name = "SERVICE_DATE")
    private LocalDate serviceDate;
    @Column(name = "ENGINE_SIZE",precision = 0)
    private Float engineSize;
    @Column(name="HP",precision = 0)
    private Long horsePower;
    @Column(name="AVERAGE",precision = 1)
    private Float averageConsumption;
    @Column(name="CITY",precision = 1)
    private Float cityConsumption;
    @Column(name="HIGHWAY",precision = 1)
    private Float highwayConsumption;
    @Column(name="PRODUCTION_YEAR",precision = 0)
    private Long yearOfProduction;

    public CarDetails(){}

    public LocalDate getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(LocalDate insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Float getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(Float engineSize) {
        this.engineSize = engineSize;
    }

    public Long getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Long horsePower) {
        this.horsePower = horsePower;
    }

    public Float getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(Float averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public Float getCityConsumption() {
        return cityConsumption;
    }

    public void setCityConsumption(Float cityConsumption) {
        this.cityConsumption = cityConsumption;
    }

    public Float getHighwayConsumption() {
        return highwayConsumption;
    }

    public void setHighwayConsumption(Float highwayConsumption) {
        this.highwayConsumption = highwayConsumption;
    }

    public Long getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Long yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public String toString() {
        return "CarDetails{" +
                "insuranceDate=" + insuranceDate +
                ", serviceDate=" + serviceDate +
                ", engineSize=" + engineSize +
                ", horsePower=" + horsePower +
                ", averageConsumption=" + averageConsumption +
                ", cityConsumption=" + cityConsumption +
                ", highwayConsumption=" + highwayConsumption +
                ", yearOfProduction=" + yearOfProduction +
                '}';
    }
}

