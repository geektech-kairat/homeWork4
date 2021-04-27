package com.example.lesson22.ui.home.HomeAdapter;

import android.os.Parcel;
import android.util.Log;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Entity
public class HomeModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String number;

    private String date;
    private String editDate = "Не был изменен";


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    at(new Date());
//    public void setDate(String date) {
//        this.date = date;
//    }

//    public String getDate() {
//        return date;
//    }private DateFormat dateFormat = new SimpleDateFormat("dd MMMM HH : mm");
//    private String date = dateFormat.form
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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




    public HomeModel(String name, String number, String date) {
        this.name = name;
        this.number = number;
        this.date = date;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    @Ignore
    public HomeModel(String name, String number, String date, String editDate) {
        this.name = name;
        this.number = number;
        this.date = date;
        this.editDate = editDate;
    }
}
