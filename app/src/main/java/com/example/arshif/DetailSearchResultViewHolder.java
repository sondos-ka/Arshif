package com.example.arshif;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DetailSearchResultViewHolder extends RecyclerView.ViewHolder {
    MyAppDatabase myAppDatabase= TestActivity.myAppDatabase;
TextView tv_date_sch,tv_cost_sch,tv_note_sch;
View SearchContainer;

    public DetailSearchResultViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_date_sch=itemView.findViewById(R.id.tvDate_line_sch);
        tv_cost_sch=itemView.findViewById(R.id.tvCost_line_sch);
        tv_note_sch=itemView.findViewById(R.id.tvNote_line_sch);

        SearchContainer=itemView.findViewById(R.id.line_container);



    }
    public void bind (final LinesEntity line){
     tv_note_sch.setText(line.getLineNote());
     tv_cost_sch.setText(String.valueOf(line.getLineCost()));
      tv_date_sch.setText(line.getLineDate());


    }
}
