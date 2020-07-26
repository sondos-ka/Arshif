package com.example.arshif;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {


    public  static   MyAppDatabase myAppDatabase;

    Button btn_Next;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        init();

        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"Arshifdb").allowMainThreadQueries(). build();
        try{
    if(myAppDatabase.myDao().CheckTypeTableEmpty()>0){

        Intent intent=new Intent(TestActivity.this,Line_multi_screen_Activity.class);
        startActivity(intent);
        finish();
    }


}catch (Exception e){}

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(TestActivity.this,DetailActivity.class);
                startActivity(intent);
                finish();


            }
        });

    }

    public void init(){


        btn_Next=findViewById(R.id.btn_Next);

    }







































}
