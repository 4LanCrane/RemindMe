package com.example.remindme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdaptor extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    private static final String TAG = "MyAdaptor";
    public List<Collections> Collections;

    /**
     * This method is used to create the adaptor for the recycler view
     * @param context
     * @param collections
     */
    public MyAdaptor(Context context, List<com.example.remindme.Collections> collections) {
        this.context = context;
        Collections = collections;
    }


    /**
     * This method is used to create the view holder for the recycler view
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_view,parent,false));
    }

    /**
     * This method is used to bind the data to the recycler view and set the on click listener for the recycler view
     * @param holder
     * @param position
     */
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

    /**
     * This method is used to get the number of items in the recycler view
     * @return
     */
    @Override
    public int getItemCount() {
        return Collections.size();
    }
}
