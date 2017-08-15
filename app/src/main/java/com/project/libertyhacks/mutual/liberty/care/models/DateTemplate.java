package com.project.libertyhacks.mutual.liberty.care.models;

import com.project.libertyhacks.mutual.liberty.care.interfaces.Mapable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by n0312809 on 8/4/2017.
 */

public class DateTemplate implements Mapable{

    private int year;
    private int month;
    private int day;

    public DateTemplate(int month, int day, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "DateTemplate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    public Map<String, Object> toMap()
    {
        HashMap<String, Object> result = new HashMap<>();
        result.put("year", year);
        result.put("month", month);
        result.put("day", day);
        return result;
    }
}
