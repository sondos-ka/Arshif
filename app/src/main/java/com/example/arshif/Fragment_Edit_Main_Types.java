package com.example.arshif;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Edit_Main_Types extends Fragment {

    View view;
    MyAppDatabase myAppDatabase=TestActivity.myAppDatabase;
    String TypeName="";
    Integer id=0;
    EditText ed_Type;
    Button btn_AddType;
    ListView lv_MainType;
    Adapter_Main_Type adapter_main_type;
    List<String> ResultData = new ArrayList<String>();




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.main_type_menu,menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        TypeName =adapter_main_type.getItem(info.position);




        switch (item.getItemId()){
            //delete case
            case R.id.deleteMainType:


                id=myAppDatabase.myDao().GetMainTypeId(TypeName);
                MainTypeEntity mainType=new MainTypeEntity();
                mainType.setTypeId(id);


                try {
                    myAppDatabase.myDao().DeleteMainType(mainType);
                    Toast.makeText(getActivity(),R.string.DeleteDone,Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    ShowMassege(R.string.ErrorCantDeleteMainType);
                    e.printStackTrace();
                }


                if (ResultData.size()!=0)  FillListViewMainType();
                else{ lv_MainType.setVisibility(view.INVISIBLE);
                }
                return true;
            default: return super.onContextItemSelected(item);


        }


    }












    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        setHasOptionsMenu(true);

        view=inflater.inflate(R.layout.fragment_fragment__edit__main__types,container,false);
        init();

        myAppDatabase= Room.databaseBuilder(getActivity(),MyAppDatabase.class,"Arshifdb").allowMainThreadQueries(). build();
        //  myDb=new ConnectionOp(this);

try{        ResultData=myAppDatabase.myDao().getMainTypes();}
catch (Exception e){}
        if(ResultData.size()!=0)  FillListViewMainType();

        btn_AddType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed_Type.getText().length()!=0){
                    MainTypeEntity typeEntity=new MainTypeEntity();
                    typeEntity.setTypeName(ed_Type.getText().toString() );

                    try {

                        myAppDatabase.myDao().AddMainType(typeEntity);
                        Toast.makeText(getActivity(),R.string.InsertDone,Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        ShowMassege(R.string.ErrorCantAddUpdateMainType);
                        e.printStackTrace();
                    }

                }
                else
                    ShowMassege(R.string.ErrorMainTypeNull);

                ed_Type.setText("");
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

        // Inflate the layout for this fragment
        return view;


    }

    public  void ShowMassege(int message){


        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);

        builder.setMessage(message);
        builder.show();
    }

    public void FillListViewMainType(){

        ResultData.clear();
        lv_MainType.setVisibility(view.VISIBLE);
        ResultData=myAppDatabase.myDao().getMainTypes();
        if(ResultData.size()!=0){

            adapter_main_type=new Adapter_Main_Type( getActivity(), ResultData);
            lv_MainType.setAdapter(adapter_main_type);
            registerForContextMenu(lv_MainType);

        }
        else    lv_MainType.setVisibility(view.INVISIBLE);

    }
    //Box for Update
    public  void ShowUpdateBox(String OldItem,final int index){
        final Dialog dialog=new Dialog(getActivity());
        dialog.setTitle(R.string.UpdateMainType);
        dialog.setContentView(R.layout.input_main_type_for_update);
        final EditText edNewName=dialog.findViewById(R.id.ed_update_maintype);
        Button btnSaveNewName=dialog.findViewById(R.id.btn_Update);
        edNewName.setText(OldItem);
        btnSaveNewName.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                ResultData.set(index,edNewName.getText().toString());
                id=myAppDatabase.myDao().GetMainTypeId(TypeName);
                MainTypeEntity mainType=new MainTypeEntity();
                mainType.setTypeId(id);
                mainType.setTypeName(edNewName.getText().toString());
                if(myAppDatabase.myDao().CheckTypeExist(mainType.getTypeName())<1) {
                    //CheckTypeExist
                    myAppDatabase.myDao().UpdateMainType(mainType);

                    Toast.makeText(getActivity(), R.string.UpdateDone, Toast.LENGTH_SHORT).show();
                    //}
                }
                else
                {
                    ShowMassege(R.string.ErrorCantAddUpdateMainType);
                }

                adapter_main_type.notifyDataSetChanged();
                FillListViewMainType();
                dialog.dismiss();

            }
        });
        dialog.show();

    }

    public void init(){


        ed_Type=view.findViewById(R.id.ed_AddType_fr) ;
        btn_AddType=view.findViewById(R.id.btn_Add_fr);
        lv_MainType=view.findViewById(R.id.lv_MainType_fr);
    }


}
