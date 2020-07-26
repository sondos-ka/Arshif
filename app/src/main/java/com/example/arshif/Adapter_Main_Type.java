package com.example.arshif;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Main_Type extends ArrayAdapter<String>{
    private LayoutInflater inflater;
    List<String> ResultData = new ArrayList<String>();
   MainTypeActivity host;
   Fragment_Edit_Main_Types Host;

    public Adapter_Main_Type(Activity host, List<String> data)
    {
        super(host,R.layout.adapter_view_main_type,data);
        inflater = host.getWindow().getLayoutInflater();
        ResultData = data;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View itemView = convertView;

        if(itemView == null)
        {
            itemView = inflater.inflate(R.layout.adapter_view_main_type,parent,false);
        }

       String currentData = ResultData.get(position);
        TextView MainTypeName = (TextView) itemView.findViewById(R.id.tvMainType);
        MainTypeName.setText(currentData);

        return itemView;
    }
    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
