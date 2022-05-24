package com.example.android.mytripproject;

public class CountryModel {
    String countryname;
    int image;

    public CountryModel(String countryname, int image) {
        this.countryname = countryname;
        this.image = image;

    }

    public String getCountryname() {
        return countryname;
    }


    public int getImage() {
        return image;
    }
}