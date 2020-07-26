package com.example.arshif;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.function.UnaryOperator;

public class ConnectionOp extends SQLiteOpenHelper {
public static final String DATABASE_NAME ="Arshif.db";
    //main Type Table
    public static final String MAIN_TYPE_TABLE_NAME ="MainTypeTable";
    public static final String MAIN_TYPE_TABLE_Col_Id ="MainTypeId";
    public static final String MAIN_TYPE_TABLE_Col_Name ="MainTypeName";




    //Details Type Table
    public static final String Details_TYPE_TABLE_NAME ="DetailsTypeTable";
    public static final String Details_TYPE_TABLE_Col_DetailId ="DetailsTypeId";
    public static final String Details_TYPE_TABLE_Col_MainTypeId ="MainTypeId";
    public static final String Details_TYPE_TABLE_Col_DetailName ="DetailsTypeName";







    public ConnectionOp(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table "+MAIN_TYPE_TABLE_NAME+"( MainTypeId Integer primary key AUTOINCREMENT,MainTypeName TEXT NOT NULL) " );
        db.execSQL("create table "+Details_TYPE_TABLE_NAME+"( DetailsTypeId Integer primary key AUTOINCREMENT,DetailsTypeName TEXT NOT NULL," +
                "MainTypeId Integer NOT NULL,CONSTRAINT FK_TypeOFDetail FOREIGN KEY (MainTypeId)  REFERENCES MAIN_TYPE_TABLE_NAME (MainTypeId))" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("drop table if exists "+MAIN_TYPE_TABLE_NAME);
db.execSQL("drop table if exists "+Details_TYPE_TABLE_NAME);

onCreate(db);
    }



    //****************************************************Functions For Main Type Table*****************************************************
    public boolean AddMainTypes(String MainTypeName){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MAIN_TYPE_TABLE_Col_Name,MainTypeName);
        long result=db.insert(MAIN_TYPE_TABLE_NAME ,null,contentValues);
        if( result==-1) return false;

        return true;
    }

    public Cursor GetMainType(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select MainTypeName from "+MAIN_TYPE_TABLE_NAME,null);
        return  result;
    };


    public Integer DeleteMainTypes(String MainTypeId){
        SQLiteDatabase db=this.getWritableDatabase();
      return  db.delete(MAIN_TYPE_TABLE_NAME,"MainTypeId=?",new String[]{ MainTypeId});

    }
    public Integer GetMainTypeID(String Name){
        Integer id=0;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select MainTypeId  as id from "+MAIN_TYPE_TABLE_NAME+" where MainTypeName=? ", new String[]{ Name });
        if (result != null && result.getCount() > 0) {
            result.moveToFirst();
            id = result.getInt(result.getColumnIndex("id"));
            result.close();
        }
        return  id;
           };
    public boolean UpdateMainTypes(String MainTypeId,String MainTypeName){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MAIN_TYPE_TABLE_Col_Id,MainTypeId);
        contentValues.put(MAIN_TYPE_TABLE_Col_Name,MainTypeName);
        long result=db.update(MAIN_TYPE_TABLE_NAME ,contentValues,"MainTypeId=?",new String[]{ MainTypeId });

        return true;
    }








    //****************************************************Functions For Details Type Table*****************************************************

    public boolean AddDetailsTypes(String DetailTypeName,Integer MainTypeId){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Details_TYPE_TABLE_Col_DetailName,DetailTypeName);
        contentValues.put(Details_TYPE_TABLE_Col_MainTypeId,MainTypeId);

        long result=db.insert(Details_TYPE_TABLE_NAME ,null,contentValues);
        if( result==-1) return false;

        return true;
    }
    public Cursor GetDetailsType(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select  DetailTypeName , MainTypeId from "+Details_TYPE_TABLE_NAME,null);
        return  result;
    };





















   }





