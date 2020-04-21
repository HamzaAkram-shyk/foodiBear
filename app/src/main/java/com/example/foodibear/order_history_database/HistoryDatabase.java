package com.example.foodibear.order_history_database;


import com.example.foodibear.Room_Database.MyDao;
import com.example.foodibear.Room_Database.UserOrder;

import androidx.room.RoomDatabase;

public abstract class HistoryDatabase extends RoomDatabase {

    public static String TableName="Order_History";
    public abstract MyConn myCon();

}
