package com.example.remindme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdaptor extends RecyclerView.Adapter<MyViewHolder>{

    Context context;
    public List<Collections> Collections;


    public MyAdaptor(Context context, List<com.example.remindme.Collections> collections) {
        this.context = context;
        Collections = collections;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_view,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.Title.setText(Collections.get(position).getTitle());
    holder.description.setText(Collections.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return Collections.size();
    }
}
