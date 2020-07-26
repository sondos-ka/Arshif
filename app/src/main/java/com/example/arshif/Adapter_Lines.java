package com.example.arshif;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Adapter_Lines extends ArrayAdapter<LinesEntity> {
    private LayoutInflater inflater;
  List<LinesEntity> ResultDataLines = new ArrayList<LinesEntity>();
    MyAppDatabase myAppDatabase=TestActivity.myAppDatabase;
   Fragment_Add_Lines host;

    public Adapter_Lines(Activity host, List<LinesEntity> dataLines)
    {
        super(host, R.layout.adapter_view_lines,dataLines);
        inflater = host.getWindow().getLayoutInflater();
        ResultDataLines  = dataLines;


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View itemView = convertView;

        if(itemView == null)
        {
            itemView = inflater.inflate(R.layout.adapter_view_lines,parent,false);
        }


        LinesEntity linesEntity=getItem(position);

        TextView Details = (TextView) itemView.findViewById(R.id.tvDetailsLines);

        TextView Cost = (TextView) itemView.findViewById(R.id.tvCost);

        TextView Date = (TextView) itemView.findViewById(R.id.tvDate);

        TextView Note = (TextView) itemView.findViewById(R.id.tvNote);



       String DetailName=myAppDatabase.detailDao().GetDetailName(linesEntity.getDetailId());
        Details.setText(DetailName);
        Cost.setText(String.valueOf(linesEntity.getLineCost()));
        Date.setText(String.valueOf(linesEntity.getLineDate()));
        Note.setText(linesEntity.getLineNote());
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
