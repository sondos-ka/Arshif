package com.example.arshif;


import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;



import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.sql.DataSource;


public class Line_multi_screen_Activity extends AppCompatActivity {
    MyAppDatabase myAppDatabase = TestActivity.myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_multi_screen_);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbarlayout);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_lines);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);
        //for first show fragment before click any button
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Add_Lines()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.nav_add_lines:
                            selectedFragment = new Fragment_Add_Lines();
                            break;
                        case R.id.nav_edit:
                            selectedFragment = new Fragment_Edit_Types();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new Fragment_search_Details();
                            break;
                        case R.id.nav_show_lines:
                            selectedFragment = new Fragment_Show_Lines();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;


                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // show menu when menu button is pressed
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_excel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // display a message when a button was pressed

        if (item.getItemId() == R.id.ExportToExcel) {


            Export();

        }


        return true;
    }
    //1-upload libs poi-3.15/poi-ooxml-3.15/poi-ooxml-3.15
    //2-add as lib
    //3-permission for write storage in manifest

    private void Export() {

        Workbook wb = new HSSFWorkbook();
        Cell cell = null;
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillBackgroundColor(HSSFColor.LIGHT_BLUE.index);

        Sheet sheet = null;
        sheet = wb.createSheet("Lines");

        Cursor cursor = myAppDatabase.linesDao().getAll();


        if (cursor.moveToFirst()) {
            do {
                String LineId = cursor.getString(cursor.getColumnIndex("LineId"));
                String DetailId = cursor.getString(cursor.getColumnIndex("DetailId"));
                String DetailName = myAppDatabase.detailDao().GetDetailName(Integer.parseInt(DetailId));
                String LineCost = cursor.getString(cursor.getColumnIndex("LineCost"));
                String LineDate = cursor.getString(cursor.getColumnIndex("LineDate"));
                String LineNote = cursor.getString(cursor.getColumnIndex("LineNote"));


                int i = 1;
                i = +cursor.getPosition() + 1;

                Row First = sheet.createRow(0);

                cell = First.createCell(0);
                cell.setCellValue(getResources().getString((R.string.LineId)));

                cell = First.createCell(1);
                cell.setCellValue(getResources().getString((R.string.Detail)));


                cell = First.createCell(2);
                cell.setCellValue(getResources().getString((R.string.Cost)));

                cell = First.createCell(3);
                cell.setCellValue(getResources().getString((R.string.DAY)));

                cell = First.createCell(4);
                cell.setCellValue(getResources().getString((R.string.Note)));


                Row row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(LineId);

                cell = row.createCell(1);
                cell.setCellValue(DetailName);

                cell = row.createCell(2);
                cell.setCellValue(LineCost);

                cell = row.createCell(3);
                cell.setCellValue(LineDate);

                cell = row.createCell(4);
                cell.setCellValue(LineNote);


            } while (cursor.moveToNext());
        }


        sheet.setColumnWidth(0, 10 * 100);
        sheet.setColumnWidth(1, 10 * 200);
        sheet.setColumnWidth(2, 10 * 300);
        sheet.setColumnWidth(3, 10 * 500);
        sheet.setColumnWidth(4, 10 * 600);


        File file = new File(getExternalFilesDir(null), "Arshif.xls");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            wb.write(fileOutputStream);
            Toast.makeText(getApplication(), R.string.ExportExcelDone, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            try {
                fileOutputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }



}
