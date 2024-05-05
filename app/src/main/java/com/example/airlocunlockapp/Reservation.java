package com.example.airlocunlockapp;

public class Reservation {
    private String localisation;
    private String image;
    private String date;
    private String name;

    public Reservation(String localisation, String image, String date, String name) {
        this.localisation = localisation;
        this.image = image;
        this.date = date;
        this.name = name;
    }

    public String getLocalisation() {
        return localisation;
    }

    public String getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}