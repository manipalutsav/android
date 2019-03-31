package com.creativecodes.maheutsav2019;

/**
 * Created by anupamchugh on 09/02/16.
 */
public class DataModel_bus {

    String name;
    String college;
    String dept;
    String version_number;
    String feature;
    String int_time;


    public DataModel_bus(String name, String college, String dept) {
        this.name = name;
        this.college = college;
        this.dept = dept;


    }

    public String getName() {
        return name;
    }

    public String getCollege() {
        return college;
    }

    public String getDept() {
        return dept;
    }
}
