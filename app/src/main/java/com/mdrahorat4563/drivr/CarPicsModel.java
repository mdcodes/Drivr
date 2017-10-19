package com.mdrahorat4563.drivr;

/**
 * Created by Michal Drahorat on 10/18/2017.
 */

public class CarPicsModel {

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    private String carName;
    private int modelYear;
    private String model;
    private String ownerUserName;

    public CarPicsModel() {}

    public CarPicsModel(String carName, int modelYear, String model)
    {
        this.carName = carName;
        this.modelYear = modelYear;
        this.model = model;
    }

}
