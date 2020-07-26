package com.example.arshif;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;





public class Adapter_Detail_Type extends ArrayAdapter<DetailTypeEntity> {
    private LayoutInflater inflater;
  List<DetailTypeEntity> ResultDataDetailsType = new ArrayList<DetailTypeEntity>();
    MyAppDatabase myAppDatabase=TestActivity.myAppDatabase;
   DetailActivity host;

    public Adapter_Detail_Type(Activity host, List<DetailTypeEntity> dataDetailsType)
    {
        super(host, R.layout.adapter_view_details_type,dataDetailsType);
        inflater = host.getWindow().getLayoutInflater();
        ResultDataDetailsType  = dataDetailsType;


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View itemView = convertView;

        if(itemView == null)
        {
            itemView = inflater.inflate(R.layout.adapter_view_details_type,parent,false);
        }

      DetailTypeEntity detailTypeEntity=getItem(position);
        TextView Details = (TextView) itemView.findViewById(R.id.tvDetailsType);

        TextView Types = (TextView) itemView.findViewById(R.id.tvMainType);



        Details.setText(detailTypeEntity.getDetailName());
        Types.setText(myAppDatabase.myDao().GetMainTypeName(detailTypeEntity.getMainTypeId()) );
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
