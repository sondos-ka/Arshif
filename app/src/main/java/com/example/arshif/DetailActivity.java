package com.example.arshif;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailActivity extends AppCompatActivity {

MyAppDatabase myAppDatabase;
    Button btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbarlayout);
        myAppDatabase=TestActivity.myAppDatabase;

        init();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_detail_container,new Fragment_Edit_Detais()).commit();
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(DetailActivity.this,Line_multi_screen_Activity.class);
                startActivity(intent);
                finish();


            }
        });


    }


    public void init(){

        btn_next=findViewById(R.id.btn_Next_Details);

    }

}

