package com.example.arshif;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.arshif.DetailTypeEntity;

import java.util.List;

@Dao
public interface DetailDao {



    @Insert
    public void AddDetails(DetailTypeEntity detailType);

    //GetDetailId
    @Query("select DetailId from DetailsTable where DetailName= :name")
    public int GetDetailId( String name);

    //GetDetailId
    @Query("select DetailName from DetailsTable where DetailId= :id")
    public String GetDetailName(int id);


    //GetTypeId
    @Query("select MainTypeId from DetailsTable where DetailId= :id")
    public int GetDetailTypeId( int id);

    //CheckUpdate
    @Query("select count(DetailId) from DetailsTable where DetailName= :name")
    public int CheckDetailExist(String name);


    @Query("select DetailId,DetailName,MainTypeId from  DetailsTable")
    public List<DetailTypeEntity> getDetails();

    @Query("select DetailName from DetailsTable")
    public List<String> getDetailsName();
    @Delete
    public void DeleteDetails(DetailTypeEntity detailType);



    @Update
    public void UpdateDetails(DetailTypeEntity detailType);


}

