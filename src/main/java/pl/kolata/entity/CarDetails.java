package pl.kolata.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by Aleksander on 2017-06-15.
 */
@Embeddable
public class CarDetails {

    @Column(name = "INSURANCE_DATE")
    @Temporal(TemporalType.DATE)
    private Date insuranceDate;
    @Column(name = "SERVICE_DATE")
    @Temporal(TemporalType.DATE)
    private Date serviceDate;
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
    @Column(name="YEARLY_DISTANCE",precision = 0)
    private Long yearlyDistance;

    public CarDetails(){}

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(Date insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
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

    public Long getYearlyDistance() {
        return yearlyDistance;
    }

    public void setYearlyDistance(Long yearlyDistance) {
        this.yearlyDistance = yearlyDistance;
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
                ", yearlyDistance=" + yearlyDistance +
                '}';
    }
}

