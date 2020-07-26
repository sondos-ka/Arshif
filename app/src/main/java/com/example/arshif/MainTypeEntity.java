package com.example.arshif;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "MainTypeTable",indices = {@Index(value = {"TypeIdName"},
        unique = true)})
public class MainTypeEntity{


    @ColumnInfo(name = "TypeId")
    @PrimaryKey(autoGenerate = true)

    private int TypeId;

    @ColumnInfo(name = "TypeIdName")
    private String TypeName;

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int typeId) {
        TypeId = typeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }
}
