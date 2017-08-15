package com.project.libertyhacks.mutual.liberty.care.models;


import com.project.libertyhacks.mutual.liberty.care.interfaces.Mapable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by n0312809 on 8/4/2017.
 */

public class User implements Mapable {

    private String userName;
    private int userAge;
    private String userGender;
    private DateTemplate userDOB;
    private String userKey;

    private String userLicenseNum;
    private DateTemplate userLicenseExpDate;

    public User()
    {

    }

    public User(String userKey, String userName, int userAge, String userGender, DateTemplate userDOB, String userLicenseNum, DateTemplate userLicenseExpDate) {
        this.userKey = userKey;
        this.userName = userName;
        this.userAge = userAge;
        this.userGender = userGender;
        this.userDOB = userDOB;
        this.userLicenseNum = userLicenseNum;
        this.userLicenseExpDate = userLicenseExpDate;
    }

    public void setUserKey(String userKey) { this.userKey = userKey; }

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

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
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
        return "{" +
                "userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userGender=" + userGender +
                ", userDOB=" + userDOB +
                ", userLicenseNum='" + userLicenseNum + '\'' +
                ", userLicenseExpDate=" + userLicenseExpDate +
                '}';
    }

    @Override
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userName", userName);
        result.put("userAge", userAge);
        result.put("userGender", String.valueOf(userGender));
        result.put("userDOB", userDOB.toMap());
        result.put("userLicenseNum", userLicenseNum);
        result.put("userLicenseExpDate", userLicenseExpDate.toMap());
        return result;
    }

    @Override
    public String getKey() {
        return userKey;
    }
}
