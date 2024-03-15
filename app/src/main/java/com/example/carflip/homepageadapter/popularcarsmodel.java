package com.example.carflip.homepageadapter;

public class popularcarsmodel {
    String carimage;
    String carname, carprice, carlocation;

    public popularcarsmodel(String carimage, String carname, String carprice, String carlocation) {
        this.carname = carname;
        this.carprice = carprice;
        this.carlocation = carlocation;
        this.carimage = carimage;
    }

    public String getCarImageResource() {
        return carimage;
    }

    public String getCarName() {
        return carname;
    }

    public String getCarPrice() {
        return carprice;
    }

    public String getCarLocation() {
        return carlocation;
    }
}
