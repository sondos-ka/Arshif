package com.example.arshif;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arshif.LinesEntity;
import com.example.arshif.R;

import java.util.ArrayList;
import java.util.List;

public class Line_Show_Adpater extends RecyclerView.Adapter {
    private List<LinesEntity> linesArray;

    public Line_Show_Adpater(List<LinesEntity> linesArray) {
        this.linesArray = linesArray;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.rv_view_lines, parent, false);

       LinesShowViewHolder linesShowViewHolder = new LinesShowViewHolder(v);

        return linesShowViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LinesShowViewHolder) {
            ((LinesShowViewHolder) holder).bind(linesArray.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return linesArray.size();
    }
}
