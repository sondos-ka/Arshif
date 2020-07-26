package com.example.arshif;

import android.database.Cursor;
import android.icu.util.LocaleData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Dao
public interface LinesDao {



    @Insert
    public void AddLine(LinesEntity linesEntity);

    //GetLineId
    @Query("select LineId from LINESTABLE where DetailId=:id and LineDate=:date")
    public int GetLineId(int id, String date);

    //check yesterday lines
    @Query("select count(LineId) from LINESTABLE where LineDate=:date")
    public int GetDateLinesCount(String date);



    @Query("select LineId,DetailId,LineCost,LineDate,LineNote from  LinesTable where LineDate=:date  order by LineCost")
    public List<LinesEntity> getLinesDay(String date);


    @Query("select LineId,DetailId,LineCost,LineDate,LineNote from  LinesTable where    strftime('%m',LineDate)=strftime('%m',(:date)) and strftime('%Y',LineDate)=strftime('%Y',(:date)) order by LineDate")
    public List<LinesEntity> getLinesMonth(String date);


    @Query("select LineId,DetailId,LineCost,LineDate,LineNote from  LinesTable where    strftime('%Y',LineDate)=strftime('%Y',(:date)) order by LineDate")
        public List<LinesEntity> getLinesYear(String date);


    @Query("select sum(LineCost) as TypeCost,DetailsTable.MainTypeId as Id  from  LinesTable inner join DetailsTable on LinesTable.DetailId=DetailsTable.DetailId  where LineDate=:date  Group by  MainTypeId  ")
    public List<AccountObject> getLinesDayAccount(String date);

    @Query("select sum(LineCost) as TypeCost,DetailsTable.MainTypeId as Id  from  LinesTable inner join DetailsTable on LinesTable.DetailId=DetailsTable.DetailId  where  strftime('%m',LineDate)=strftime('%m',(:date)) and strftime('%Y',LineDate)=strftime('%Y',(:date))  Group by  MainTypeId  ")
    public List<AccountObject> getLinesMonthAccount(String date);

    @Query("select sum(LineCost) as TypeCost,DetailsTable.MainTypeId as Id  from  LinesTable inner join DetailsTable on LinesTable.DetailId=DetailsTable.DetailId  where  strftime('%Y',LineDate)=strftime('%Y',(:date))  Group by  MainTypeId  ")
    public List<AccountObject> getLinesYearAccount(String date);

    @Query("select LineId,DetailId,LineCost,LineDate,LineNote from  LinesTable where DetailId=:Id  order by LineDate  desc")
    public List<LinesEntity> getDetailsSearch(int Id);



    @Query("select LineId,DetailId,LineCost,LineDate,LineNote from  LinesTable   order by LineDate  desc")
    public Cursor getAll();




    @Delete
    public void DeleteLine(LinesEntity linesEntity);



    @Update
    public void UpdateLine(LinesEntity linesEntity);


}

