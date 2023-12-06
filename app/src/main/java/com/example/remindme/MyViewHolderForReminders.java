package com.example.remindme;


import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderForReminders extends RecyclerView.ViewHolder {
    private final Context context;
    TextView Title;
    TextView Date;
    TextView Time;
    ImageButton DeleteButton;

    /**
     * This method is used to create the view holder for the recycler view
     *
     * @param itemView
     */
    public MyViewHolderForReminders(@NonNull View itemView) {
        super(itemView);
        Title = itemView.findViewById(R.id.Title2);
        Date = itemView.findViewById(R.id.Date);
        Time = itemView.findViewById(R.id.Time);
        context = itemView.getContext();
        DeleteButton = itemView.findViewById(R.id.DeleteButton);
    }


}