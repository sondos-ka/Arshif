package com.example.arshif;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MyDao {


    @Insert
    public void AddMainType(MainTypeEntity maintype);

    //GetMainTypeId
    @Query("select TypeId from MainTypeTable where TypeIdName= :name")
    public int GetMainTypeId(String name);

    //GetMainTypeName
    @Query("select TypeIdName from MainTypeTable where TypeId= :id")
    public String GetMainTypeName(int id);

    @Query("select TypeIdName from MainTypeTable")
    public List<String> getMainTypes();

    //CheckUpdate
    @Query("select count(TypeId) from MainTypeTable where TypeIdName= :name")
    public int CheckTypeExist(String name);



    //Check Main TypeTable Empty
    @Query("select count(TypeId) from MainTypeTable ")
    public int CheckTypeTableEmpty();





    @Delete
    public void DeleteMainType(MainTypeEntity maintype);



    @Update
    public void UpdateMainType(MainTypeEntity maintype);






}
