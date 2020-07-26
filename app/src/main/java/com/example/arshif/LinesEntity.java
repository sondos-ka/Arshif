package com.example.arshif;

import android.icu.util.LocaleData;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;


@Entity(tableName = "LinesTable",foreignKeys = @ForeignKey(entity = DetailTypeEntity.class,
        parentColumns = "DetailId",
        childColumns = "DetailId"),indices = {@Index(value = {"DetailId","LineDate"},
        unique = true)})

public class LinesEntity {


    @ColumnInfo(name = "LineId")
    @PrimaryKey(autoGenerate = true)
    private int LineId;

    @ColumnInfo(name = "DetailId")
    private int DetailId;



    @ColumnInfo(name = "LineCost")
    @NonNull
    private float LineCost;

    @ColumnInfo(name = "LineDate")
    @NonNull

    private String LineDate;

    @ColumnInfo(name = "LineNote")
    private String LineNote;


    public int getLineId() {
        return LineId;
    }

    public void setLineId(int lineId) {
        LineId = lineId;
    }

    public int getDetailId() {
        return DetailId;
    }

    public void setDetailId(int detailId) {
        DetailId = detailId;
    }

    public float getLineCost() {
        return LineCost;
    }

    public void setLineCost(float lineCost) {
        LineCost = lineCost;
    }

    public String getLineNote() {
        return LineNote;
    }

    public void setLineNote(String lineNote) {
        LineNote = lineNote;
    }

    @NonNull
    public String getLineDate() {
        return LineDate;
    }

    public void setLineDate(@NonNull String lineDate) {
        LineDate = lineDate;
    }
}
