package com.creativecodes.maheutsav2019;

/**
 * Created by anupamchugh on 09/02/16.
 */
public class DataModelauto {

    String name;
    String venue;
    String time,org,id;
    String desc;



    public DataModelauto(String name, String venue, String time,String org,String desc,String id ) {
        this.name=name;
        this.venue=venue;
        this.time=time;
        this.id=id;
        this.org=org;
        this.desc=desc;



    }

    public String getName() {
        return name;
    }

    public String getVenue() {
        return venue;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getTime() {
        return time;
    }

    public String getOrg() {
        return org;
    }
}
