package com.project.libertyhacks.mutual.liberty.care.models;

import com.project.libertyhacks.mutual.liberty.care.interfaces.Mapable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrewcunningham on 8/15/17.
 */

public class Car implements Mapable {

    private String vin;
    private String name;
    private String make;
    private String model;
    private String ownerId;
    private int year;
    private int miles;

    public Car(String vin, String name, String make, String model, int year, int miles, String ownerId) {
        this.vin = vin;
        this.name = name;
        this.make = make;
        this.model = model;
        this.year = year;
        this.miles = miles;
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public void setValues(Car newCar) {
        this.make = newCar.getMake();
        this.miles = newCar.getMiles();
        this.model = newCar.getModel();
        this.name = newCar.getName();
        this.vin = newCar.getVin();
        this.year = newCar.getYear();
    }

    public Car() {

    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("make", make);
        result.put("model", model);
        result.put("year", year);
        result.put("miles", miles);
        result.put("vin", vin);
        result.put("ownerId", ownerId);
        return result;
    }

    public String getKey() {
        return vin;
    }

    @Override
    public String toString() {
        return "Car{" +
                "vin='" + vin + '\'' +
                ", name='" + name + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", miles=" + miles +
                '}';
    }
}
