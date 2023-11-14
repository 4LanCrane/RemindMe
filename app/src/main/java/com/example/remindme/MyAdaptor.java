package com.example.remindme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdaptor extends RecyclerView.Adapter<MyViewHolder> {

    Context context;

    private static final String TAG = "MyAdaptor";
    public List<Collections> Collections;
    private View.OnClickListener onClickListener;

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

    holder.itemView.setOnClickListener(v -> {
        Log.d(TAG, "onClick: " + Collections.get(position).getId());
        Intent intent = new Intent(context, ReminderList.class);
        intent.putExtra("collectionId", Collections.get(position).getId());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    });
    }

    @Override
    public int getItemCount() {
        return Collections.size();
    }
}
