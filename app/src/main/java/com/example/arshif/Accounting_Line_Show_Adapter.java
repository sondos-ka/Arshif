package com.example.arshif;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Accounting_Line_Show_Adapter extends RecyclerView.Adapter {
    private List<AccountObject> CostArray;

    public Accounting_Line_Show_Adapter(List<AccountObject> CostArray) {
        this.CostArray = CostArray;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.rv_view_acounting, parent, false);

       LinesShowTypeAccountingViewHolder linesShowTypeAccountingViewHolder = new LinesShowTypeAccountingViewHolder(v);

        return linesShowTypeAccountingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LinesShowTypeAccountingViewHolder) {
            ((LinesShowTypeAccountingViewHolder) holder).bind(CostArray.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return CostArray.size();
    }
}
