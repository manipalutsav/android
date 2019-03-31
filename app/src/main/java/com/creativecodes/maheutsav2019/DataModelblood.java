package com.creativecodes.maheutsav2019;

/**
 * Created by anupamchugh on 09/02/16.
 */
public class DataModelblood {

    String dname;
    String dtype;
    String dcontact;



    public DataModelblood(String dname, String dtype, String dcontact ) {
        this.dname=dname;
        this.dtype=dtype;
        this.dcontact=dcontact;



    }

    public String getDname() {
        return dname;
    }

    public String getDtype() {
        return dtype;
    }

    public String getDcontact() {
        return dcontact;
    }
}
