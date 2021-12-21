package com.example.user.work.models;

import android.graphics.Bitmap;

public class Themen {
    public String themenName;
    public String description;
    public String thema1;
    public String thema2;
    public String thema3;
    public Bitmap logo;

    public Themen(String themenName,
                  String description,
                  String thema1,
                  String thema2,
                  String thema3) {

        this.themenName = themenName;
        this.description = description;
        this.thema1 = thema1;
        this.thema2 = thema2;
        this.thema3 = thema3;
    }
}
