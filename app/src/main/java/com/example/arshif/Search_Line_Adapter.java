package com.example.arshif;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Search_Line_Adapter extends RecyclerView.Adapter {
    private List<LinesEntity> ResultArray;

    public Search_Line_Adapter(List<LinesEntity> ResultArray) {
        this.ResultArray=ResultArray;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.rv_view_search, parent, false);

       DetailSearchResultViewHolder detailSearchResultViewHolder = new DetailSearchResultViewHolder(v);

        return detailSearchResultViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DetailSearchResultViewHolder) {
            ((DetailSearchResultViewHolder) holder).bind(ResultArray.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return ResultArray.size();
    }
}
