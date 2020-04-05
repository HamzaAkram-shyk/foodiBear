package com.example.foodibear.Room_Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MyDao {

    @Insert
    public void addOrder(UserOrder userOrder);

    @Query("Select * from Orders")
    public List<UserOrder> getOrders();

}
