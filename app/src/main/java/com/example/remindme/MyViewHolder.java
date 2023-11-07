package com.example.remindme;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{

    TextView Title;



    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        Title = itemView.findViewById(R.id.Title);

    }


}


