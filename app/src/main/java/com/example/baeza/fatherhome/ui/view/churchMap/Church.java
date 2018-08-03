package com.example.baeza.fatherhome.ui.view.churchMap;

public class Church {

    private String address, lat, lon, time;

    public Church(String address, String lat, String lon, String time){
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
