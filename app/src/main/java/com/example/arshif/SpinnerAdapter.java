package com.example.arshif;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class SpinnerAdapter extends ArrayAdapter {
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initview(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return  initview(position, convertView, parent);
    }

    public SpinnerAdapter(@NonNull Context context, List<String> types) {
        super(context, 0,types);
    }

    private View initview(int position,View convertView,ViewGroup parent){

if(convertView==null){
    convertView= LayoutInflater.from(getContext()).inflate(R.layout.spinnerstyle,parent,false);

}

        TextView tvSpinner=convertView.findViewById(R.id.tv_spinner);
    String current_type= (String) getItem(position);

    if(current_type!=null)
    tvSpinner.setText(current_type);

    return  convertView;
    }


}
