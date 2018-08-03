package com.example.baeza.fatherhome.ui.view.churchMap;

public class GrowthGroup {

    private String lat, lon, name, phone, time;

    public GrowthGroup (String lat, String lon, String name, String phone, String time){
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.phone = phone;
        this.time = time;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
