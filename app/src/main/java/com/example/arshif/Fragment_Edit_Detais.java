package com.example.arshif;

import android.app.Dialog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Edit_Detais extends Fragment {
    View view;
    EditText ed_Detail;
    Button btn_AddDetail;
    Spinner TypeSpiner;
    ListView lv_MainDetails;
    MyAppDatabase myAppDatabase=TestActivity.myAppDatabase;
    String DetailsName="";
    Integer id=0;
    String tutorialsName="";
    List<String> ResultTypes = new ArrayList<String>();
   // List<String> ResultDetails = new ArrayList<String>();
    List<DetailTypeEntity> ArrayDetails;
    Adapter_Detail_Type adapter_detail_type;
    private SpinnerAdapter spinnerAdapter;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.main_type_menu,menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        DetailsName =adapter_detail_type.getItem(info.position).getDetailName();

        switch (item.getItemId()){
            //delete case
            case R.id.deleteMainType:

                id=myAppDatabase.detailDao().GetDetailId(DetailsName);
                DetailTypeEntity DetailType=new DetailTypeEntity();
                DetailType.setDetailId(id);


                try {
                    myAppDatabase.detailDao().DeleteDetails(DetailType);

                    Toast.makeText(getActivity(),R.string.DeleteDone,Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    ShowMassege(R.string.ErrorDeleteDetails);
                    e.printStackTrace();
                }
                FillListViewDetailsType();
                return true;

            default: return super.onContextItemSelected(item);   }
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);


        view= inflater.inflate(R.layout.fragment_fragment__edit__detais, container, false);

       init();

       ArrayDetails = new ArrayList<DetailTypeEntity>();
        ArrayDetails= myAppDatabase.detailDao().getDetails();

        // adapter_detail_type=new Adapter_Detail_Type(this,ArrayDetails);
        if (ArrayDetails.size()!=0) FillListViewDetailsType();
        FillSpinner();


        btn_AddDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed_Detail.getText().length()!=0){
                    DetailTypeEntity DetailEntity=new DetailTypeEntity();
                    DetailEntity.setDetailName(ed_Detail.getText().toString() );
                    DetailEntity.setMainTypeId(myAppDatabase.myDao().GetMainTypeId(tutorialsName));

                    try {
                        myAppDatabase.detailDao().AddDetails(DetailEntity);
                        Toast.makeText(getActivity(),R.string.InsertDone,Toast.LENGTH_SHORT).show();

                    }
                    catch (Exception e) {
                        ShowMassege(R.string.ErrorCantAddUpdateDetail);
                        e.printStackTrace();
                    }
                }
                else
                    ShowMassege(R.string.ErrorDetailNull);

                ed_Detail.setText("");
                FillListViewDetailsType();

            }
        });
        //for update
        lv_MainDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailsName=ArrayDetails.get(position).getDetailName();
                ShowUpdateBox(DetailsName,position);

            }
        });

        return view;
    }

    public  void ShowMassege(int message){

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.show();
    }

    public void init(){

        ed_Detail=(EditText) view.findViewById(R.id.ed_AddDetail_fr) ;
        btn_AddDetail=(Button) view.findViewById(R.id.btn_AddDetails_fr);
        lv_MainDetails=(ListView) view.findViewById(R.id.lv_MainDetail_fr);
        TypeSpiner=(Spinner) view.findViewById(R.id.sp_Types_fr);

    }
    public void FillListViewDetailsType(){

        ArrayDetails.clear();
       lv_MainDetails.setVisibility(view.VISIBLE);
        ArrayDetails= myAppDatabase.detailDao().getDetails();
        if(ArrayDetails.size()!=0){

            adapter_detail_type=new Adapter_Detail_Type(getActivity(), ArrayDetails);
        lv_MainDetails.setAdapter(adapter_detail_type);
        registerForContextMenu( lv_MainDetails);}
        else    lv_MainDetails.setVisibility(view.INVISIBLE);
    }

    //Box for Update
    public  void ShowUpdateBox(String OldItem,final int index){
        final Dialog dialog=new Dialog(getActivity());
        dialog.setTitle(R.string.UpdateDetailType);
        dialog.setContentView(R.layout.input_main_type_for_update);
        final EditText edNewName=dialog.findViewById(R.id.ed_update_maintype);
        Button btnSaveNewName=dialog.findViewById(R.id.btn_Update);
        edNewName.setText(OldItem);
        btnSaveNewName.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                id=myAppDatabase.detailDao().GetDetailId(DetailsName);
                int Typeid=myAppDatabase.detailDao().GetDetailTypeId(id);
                DetailTypeEntity DetailType=new DetailTypeEntity();
                DetailType.setDetailId(id);
                DetailType.setDetailName(edNewName.getText().toString());
                DetailType.setMainTypeId(Typeid);
//
                //ArrayDetails.set(index,DetailType);

                if(myAppDatabase.detailDao().CheckDetailExist(DetailType.getDetailName())<1) {
                    //CheckDetailExist
                    myAppDatabase.detailDao().UpdateDetails(DetailType);
                    //Cant edit type here
                    Toast.makeText(getActivity(), R.string.UpdateDone, Toast.LENGTH_SHORT).show();
                }
                else
                    ShowMassege(R.string.ErrorCantAddUpdateDetail);
                adapter_detail_type.notifyDataSetChanged();
                FillListViewDetailsType();
                dialog.dismiss();

            }
        });
        dialog.show();

    }

    public  void FillSpinner(){
        ResultTypes.clear();
        ResultTypes=myAppDatabase.myDao().getMainTypes();
        spinnerAdapter =new SpinnerAdapter(getActivity(),ResultTypes);
         TypeSpiner.setAdapter(spinnerAdapter);

        TypeSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tutorialsName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }



}
