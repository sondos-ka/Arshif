package com.example.arshif;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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

public class MainTypeActivity extends AppCompatActivity {/*
    String TypeName="";
    Integer id=0;
  ConnectionOp myDb;
  EditText ed_Type;
  Button btn_AddType;
  ListView lv_MainType;
    Adapter_Main_Type adapter_main_type;


    List<String> ResultData = new ArrayList<String>();

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main_type_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        TypeName =adapter_main_type.getItem(info.position);
        id=myDb.GetMainTypeID(TypeName);



        switch (item.getItemId()){
            //delete case
            case R.id.deleteMainType:



                Integer DeletRows=  myDb.DeleteMainTypes((id).toString());
                if(DeletRows>0) Toast.makeText(getApplicationContext(),R.string.DeleteDone,Toast.LENGTH_SHORT).show();
                FillListViewMainType();
                return true;





         default: return super.onContextItemSelected(item);   }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_type);
        init();
        myDb=new ConnectionOp(this);
        FillListViewMainType();


        btn_AddType.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             boolean IsInserted= myDb.AddMainTypes(ed_Type.getText().toString());

             if(IsInserted=true){
                 Toast.makeText(getApplicationContext(),R.string.InsertDone,Toast.LENGTH_SHORT).show();
             }

             FillListViewMainType();

         }
     });
        //for update
        lv_MainType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TypeName=ResultData.get(position);
                ShowUpdateBox(TypeName,position);

            }
        });
    }
    public  void ShowMassege(String title,String message){


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void init(){

       ed_Type=findViewById(R.id.ed_AddType) ;
       btn_AddType=findViewById(R.id.btn_Add);
        lv_MainType=findViewById(R.id.lv_MainType);

    }
    public void FillListViewMainType(){


        Cursor result=myDb.GetMainType();
        if(result.getCount()==0){
            ShowMassege("Error","no thing found");
        }

       ResultData.clear();
        while (result.moveToNext()){
            ResultData.add(result.getString(0));
          //  adapter_main_type=new Adapter_Main_Type(MainTypeActivity.this, ResultData);
            lv_MainType.setAdapter(adapter_main_type);




            registerForContextMenu(lv_MainType);


        }

    }



    //Box for Update
    public  void ShowUpdateBox(String OldItem,final int index){
     final Dialog dialog=new Dialog(MainTypeActivity.this);
     dialog.setTitle(R.string.UpdateMainType);
     dialog.setContentView(R.layout.input_main_type_for_update);
        final EditText edNewName=dialog.findViewById(R.id.ed_update_maintype);
        Button btnSaveNewName=dialog.findViewById(R.id.btn_Update);
        edNewName.setText(OldItem);
        btnSaveNewName.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                ResultData.set(index,edNewName.getText().toString());
                id=myDb.GetMainTypeID(TypeName);
       if( myDb.UpdateMainTypes(id.toString(),edNewName.getText().toString())){

        Toast.makeText(getApplicationContext(),R.string.UpdateDone,Toast.LENGTH_SHORT).show();}


    adapter_main_type.notifyDataSetChanged();
       FillListViewMainType();
        dialog.dismiss();

            }
        });
        dialog.show();

    }
*/



}
