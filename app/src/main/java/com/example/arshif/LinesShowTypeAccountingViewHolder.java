package com.example.arshif;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinesShowTypeAccountingViewHolder extends RecyclerView.ViewHolder {
    MyAppDatabase myAppDatabase= TestActivity.myAppDatabase;
TextView tv_typeNameLine,tv_Account;
View AccountContainer;

    public LinesShowTypeAccountingViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_Account=itemView.findViewById(R.id.tvTypeCostAccount);
        tv_typeNameLine=itemView.findViewById(R.id.tvTypeNameAccount);
        AccountContainer=itemView.findViewById(R.id.Account_container);


    }
    public void bind (final AccountObject Account){

        tv_Account.setText(Account.getTypeCost().toString());


        String TypeName=myAppDatabase.myDao().GetMainTypeName(Account.getId());
        tv_typeNameLine.setText(TypeName);

    }
}
