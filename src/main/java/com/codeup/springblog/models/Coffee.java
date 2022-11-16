package com.codeup.springblog.models;
// This is a POJO (Plain Old Java Object)
public class Coffee {

    private String roast;

    private String origin;

    private String brand;

    public Coffee() {
    }

    public Coffee(String brand) {
        this.brand = brand;
    }
    public Coffee(String roast, String brand) {
        this.roast = roast;
        this.brand = brand;
    }

    public String getRoast() {
        return roast;
    }

    public void setRoast(String roast) {
        this.roast = roast;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
