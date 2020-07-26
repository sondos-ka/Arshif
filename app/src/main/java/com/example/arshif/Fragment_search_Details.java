package com.example.arshif;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_search_Details extends Fragment {


    View view;
    MyAppDatabase myAppDatabase=TestActivity.myAppDatabase;
    RecyclerView rv_search_result;
    TextView tv_Type;
    Spinner sp_Details;
    List<String> ResultDetails = new ArrayList<String>();
    List<LinesEntity> ResultSearch ;
    String ChosenDetail="";
    Search_Line_Adapter search_adapter;
    private SpinnerAdapter spinnerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_search_details,container,false);
        init();


        FillSpinner();

        return view;

    }



    public void init(){
        sp_Details=view.findViewById(R.id.sp_details_search);
        rv_search_result=view.findViewById(R.id.rv_detail_search);
        tv_Type=view.findViewById(R.id.tv_Type_line_sch);
    }


    public  void FillSpinner(){
        ResultDetails.clear();
        ResultDetails=myAppDatabase.detailDao().getDetailsName();

        spinnerAdapter =new SpinnerAdapter(getActivity(),ResultDetails);
        sp_Details.setAdapter(spinnerAdapter);

        sp_Details.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ChosenDetail = parent.getItemAtPosition(position).toString();
                ResultSearch = new ArrayList<LinesEntity>();
                ResultSearch= myAppDatabase.linesDao().getDetailsSearch(myAppDatabase.detailDao().GetDetailId(ChosenDetail));
                int typeid=myAppDatabase.detailDao().GetDetailTypeId(myAppDatabase.detailDao().GetDetailId(ChosenDetail));
               tv_Type.setText(myAppDatabase.myDao().GetMainTypeName(typeid));
                FillRecyclerView();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }

    public void FillRecyclerView(){


        rv_search_result.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        search_adapter = new Search_Line_Adapter(ResultSearch);
        rv_search_result.setAdapter(search_adapter);


    }
}
