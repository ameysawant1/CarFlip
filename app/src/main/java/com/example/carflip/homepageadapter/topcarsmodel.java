package com.example.carflip.homepageadapter;

public class topcarsmodel {
    private String carName;
    private String carPrice;

    public topcarsmodel() {

    }

    public topcarsmodel(String carName, String carPrice) {
        this.carName = carName;
        this.carPrice = carPrice;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }
}
