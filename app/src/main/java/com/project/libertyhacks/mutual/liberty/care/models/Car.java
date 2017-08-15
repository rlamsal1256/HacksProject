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
    private int year;
    private int miles;

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



    public Car()
    {

    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("make", make);
        result.put("model", model);
        result.put("year", year);
        result.put("miles", miles);
        return result;
    }

    public String getKey() {
        return vin;
    }
}
