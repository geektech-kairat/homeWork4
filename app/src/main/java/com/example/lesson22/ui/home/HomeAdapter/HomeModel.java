package com.example.lesson22.ui.home.HomeAdapter;

import android.os.Parcel;

import java.io.Serializable;
import java.util.Random;

public class HomeModel implements Serializable {
    private String name;
    private String number;
    private int id = new Random().nextInt();

    public int getId() {
        return id;
    }

    public HomeModel(String name, String number) {
        this.name = name;
        this.number = number;
    }

    protected HomeModel(Parcel in) {
        name = in.readString();
        number = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {

        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
