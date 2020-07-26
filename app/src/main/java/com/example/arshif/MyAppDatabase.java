package com.example.arshif;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {MainTypeEntity.class ,DetailTypeEntity.class,LinesEntity.class}, version =107
        ,exportSchema = false)

public abstract class MyAppDatabase extends RoomDatabase
{


    public abstract MyDao myDao();
    public abstract  DetailDao detailDao();
    public abstract LinesDao linesDao();
}
