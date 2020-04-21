package com.example.foodibear.Room_Database;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {UserOrder.class},version = 2)
public abstract class Database extends RoomDatabase {


 public abstract MyDao myDao();



}
