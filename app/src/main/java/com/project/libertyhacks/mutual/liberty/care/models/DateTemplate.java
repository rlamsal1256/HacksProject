package com.project.libertyhacks.mutual.liberty.care.models;

/**
 * Created by n0312809 on 8/4/2017.
 */

public class DateTemplate {

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
}
