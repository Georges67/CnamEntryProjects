package cnam.com.myfirstgpsapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class GpsPath implements Serializable {

    private long id;
    private String name;
    private List<GpsPoint> data = new ArrayList<>();

    public GpsPath()  {
    }

    public GpsPath(long id) {
        this.id = id;
    }

    public GpsPath(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String firstName) {
        this.name = firstName;
    }

    public String getGpsPointTableName() {
        return MyGpsPathDbHelper.TABLE_PATH_POINTS + String.valueOf(this.id);
    }

    public List<GpsPoint> getData() {
        return this.data;
    }
    public void setData(List<GpsPoint> data) {
        this.data = data;
    }
    public void addGpsPoint(GpsPoint data) {
        this.data.add(data);
    }
    public void addGpsPoint(double latitude, double longitude) {
        GpsPoint point = new GpsPoint(latitude, longitude);
        this.data.add(point);
    }

    @Override
    public String toString()  {
        return this.name;
    }

}