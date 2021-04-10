package com.example.lesson22.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lesson22.ui.home.HomeAdapter.HomeModel;

@Database(entities = {HomeModel.class}, version = 1)
public abstract class FillDatabase extends RoomDatabase {
    public abstract FillDao fillDao();
}
