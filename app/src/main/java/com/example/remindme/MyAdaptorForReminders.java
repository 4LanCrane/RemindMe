package com.example.remindme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdaptorForReminders extends RecyclerView.Adapter<MyViewHolderForReminders> {

    Context context2;

    private static final String TAG = "MyAdaptorForReminders";
    public List<Reminder> Reminder;
    private View.OnClickListener onClickListener;

    public MyAdaptorForReminders(Context context, List<com.example.remindme.Reminder> reminders) {
        this.context2 = context;
        Reminder = reminders;
    }

    @Override
    public MyViewHolderForReminders onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolderForReminders(LayoutInflater.from(context2).inflate(R.layout.reminderlist_rec, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolderForReminders holder, @SuppressLint("RecyclerView") int position) {

        holder.Title.setText(Reminder.get(position).getReminderTitle());
        holder.Time.setText(Reminder.get(position).getReminderTime());
        holder.Date.setText(Reminder.get(position).getReminderDate());
        holder.DeleteButton.setOnClickListener(v -> {
            Log.d(TAG, "onClick: " + Reminder.get(position).getReminderTitle());
            DBHandlerReminders db = new DBHandlerReminders(context2,""+Reminder.get(position).getReminderId()+"");
            db.removeReminder(Reminder.get(position).getReminderTitle());
            Reminder.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, Reminder.size());

        });


    }

    ;


    @Override
    public int getItemCount() {
        return Reminder.size();
    }
}
