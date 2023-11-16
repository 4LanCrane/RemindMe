package com.example.remindme;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderForReminders extends RecyclerView.ViewHolder{
    private final Context context;
    TextView Title;
    TextView Date;
    TextView Time;


    public MyViewHolderForReminders(@NonNull View itemView) {
        super(itemView);
        Title = itemView.findViewById(R.id.Title2);
        Date = itemView.findViewById(R.id.Date);
        Time = itemView.findViewById(R.id.Time);
        context = itemView.getContext();

    }



}