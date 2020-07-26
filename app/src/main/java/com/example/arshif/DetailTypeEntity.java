package com.example.arshif;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;



@Entity(tableName = "DetailsTable",foreignKeys = @ForeignKey(entity = MainTypeEntity.class,
        parentColumns = "TypeId",
        childColumns = "MainTypeId"),indices = {@Index(value = {"DetailName"},
        unique = true)})

//        onDelete = CASCADE
public class DetailTypeEntity {


    @ColumnInfo(name = "DetailId")
    @PrimaryKey(autoGenerate = true)
    private int DetailId;

    @ColumnInfo(name = "DetailName")
    private String DetailName;


    @ColumnInfo(name = "MainTypeId")

    private int MainTypeId;



    public int getDetailId() {
        return DetailId;
    }

    public void setDetailId(int detailId) {
        DetailId = detailId;
    }

    public String getDetailName() {
        return DetailName;
    }

    public void setDetailName(String detailName) {
        DetailName = detailName;
    }

    public int getMainTypeId() {
        return MainTypeId;
    }

    public void setMainTypeId(int mainTypeId) {
        MainTypeId = mainTypeId;
    }
}
