package com.example.arshif;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
   Timer timer;
   MyAppDatabase myAppDatabase=TestActivity.myAppDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbarlayout);


        timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent=new Intent(MainActivity.this,TestActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}
