package com.example.arshif;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arshif.LinesEntity;
import com.example.arshif.MyAppDatabase;
import com.example.arshif.R;
import com.example.arshif.TestActivity;

public class LinesShowViewHolder extends RecyclerView.ViewHolder {
    MyAppDatabase myAppDatabase= TestActivity.myAppDatabase;
TextView tv_typeline,tv_detailline,tv_dateline,tv_costline,tv_noteline;
View LineContainer;

    public LinesShowViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_typeline=itemView.findViewById(R.id.tvTypeLines);
        tv_detailline=itemView.findViewById(R.id.tv_Detail_line);
        tv_dateline=itemView.findViewById(R.id.tvDate_line);
        tv_costline=itemView.findViewById(R.id.tvCost_line);
        tv_noteline=itemView.findViewById(R.id.tvNote_line);
        LineContainer=itemView.findViewById(R.id.line_container);



    }
    public void bind (final LinesEntity line){
       tv_noteline.setText(line.getLineNote());
       tv_costline.setText(String.valueOf(line.getLineCost()));
       tv_dateline.setText(line.getLineDate());
       tv_detailline.setText(myAppDatabase.detailDao().GetDetailName(line.getDetailId()));
      int typeid=myAppDatabase.detailDao().GetDetailTypeId(line.getDetailId());
      tv_typeline.setText(myAppDatabase.myDao().GetMainTypeName(typeid));

    }
}
