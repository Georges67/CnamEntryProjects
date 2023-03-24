package cnam.com.myfirstgpsapp;

import java.io.Serializable;

public class GpsPoint implements Serializable {

    public double latitude;
    public double longitude;

    public GpsPoint()  {
    }

    public GpsPoint(double latitude,double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
