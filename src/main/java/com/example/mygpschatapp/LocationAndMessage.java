package com.example.mygpschatapp;

public class LocationAndMessage {
    public double latitude;
    public double longitude;
    public String message;
    public String date;

    public LocationAndMessage(){

    }

    public LocationAndMessage(double latitude, double longitude, String message, String date){
        this.latitude = latitude;
        this.longitude = longitude;
        this.message = message;
        this.date = date;
    }
}
