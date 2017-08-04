package com.project.libertyhacks.mutual.liberty.care.models;


import java.util.Date;

/**
 * Created by n0312809 on 8/4/2017.
 */

public class User {

    private String userName;
    private int userAge;
    private char userGender;
    private DateTemplate userDOB;

    private String userLicenseNum;
    private DateTemplate userLicenseExpDate;

    public User(String userName, int userAge, char userGender, DateTemplate userDOB, String userLicenseNum, DateTemplate userLicenseExpDate) {
        this.userName = userName;
        this.userAge = userAge;
        this.userGender = userGender;
        this.userDOB = userDOB;
        this.userLicenseNum = userLicenseNum;
        this.userLicenseExpDate = userLicenseExpDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public DateTemplate getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(DateTemplate userDOB) {
        this.userDOB = userDOB;
    }

    public String getUserLicenseNum() {
        return userLicenseNum;
    }

    public void setUserLicenseNum(String userLicenseNum) {
        this.userLicenseNum = userLicenseNum;
    }

    public DateTemplate getUserLicenseExpDate() {
        return userLicenseExpDate;
    }

    public void setUserLicenseExpDate(DateTemplate userLicenseExpDate) {
        this.userLicenseExpDate = userLicenseExpDate;
    }

    public char getUserGender() {
        return userGender;
    }

    public void setUserGender(char userGender) {
        this.userGender = userGender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userName.equals(user.userName)) return false;
        return userLicenseNum.equals(user.userLicenseNum);

    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + userLicenseNum.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userGender=" + userGender +
                ", userDOB=" + userDOB +
                ", userLicenseNum='" + userLicenseNum + '\'' +
                ", userLicenseExpDate=" + userLicenseExpDate +
                '}';
    }
}
