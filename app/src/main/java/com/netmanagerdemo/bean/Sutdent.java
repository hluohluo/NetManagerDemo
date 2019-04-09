package com.netmanagerdemo.bean;

/**
 * Created by hluo on 2019/4/9.
 */
public class Sutdent {

    /**
     * name : 李逍遥
     * gender : 男
     */

    private String name;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Sutdent{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
