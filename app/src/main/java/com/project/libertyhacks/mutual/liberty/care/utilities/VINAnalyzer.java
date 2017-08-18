package com.project.libertyhacks.mutual.liberty.care.utilities;

import com.project.libertyhacks.mutual.liberty.care.models.Car;

/**
 * Created by Raymond on 8/17/2017.
 *
 * From a VIN, a VINAnalyzer can determine the attributes of a car (e.g., make, model)
 */

public class VINAnalyzer {

    // Creates a Car object, setting the attributes vin, make, model, and year.
    // Returns the Car object.
    public static Car getCar(String vin) {
        Car car = new Car();

        // Find the make, model, and year
        // These are hardcoded for now
        String make = "Honda";
        String model = "Accord";
        int year = 2013;

        car.setVin(vin);
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);

        return car;
    }

}
