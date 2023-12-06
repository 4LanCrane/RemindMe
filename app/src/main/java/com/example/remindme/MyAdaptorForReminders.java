package com.example.remindme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdaptorForReminders extends RecyclerView.Adapter<MyViewHolderForReminders> {

    Context context2;

    private static final String TAG = "MyAdaptorForReminders";
    public List<Reminder> Reminder;

    /**
     * This method is used to create the adaptor for the recycler view
     *
     * @param context
     * @param reminders
     */

    public MyAdaptorForReminders(Context context, List<com.example.remindme.Reminder> reminders) {
        this.context2 = context;
        Reminder = reminders;
    }


    /**
     * This method is used to create the view holder for the recycler view
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolderForReminders onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolderForReminders(LayoutInflater.from(context2).inflate(R.layout.reminderlist_rec, parent, false));
    }


    /**
     * This method is used to bind the data to the recycler view and set the on click listener for the recycler view used to delete reminders
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolderForReminders holder, @SuppressLint("RecyclerView") int position) {

        holder.Title.setText(Reminder.get(position).getReminderTitle());
        holder.Time.setText(Reminder.get(position).getReminderTime());
        holder.Date.setText(Reminder.get(position).getReminderDate());
        holder.DeleteButton.setOnClickListener(v -> {
            Log.d(TAG, "onClick: the reminder title is: " + Reminder.get(position).getReminderTitle());
            DBHandlerReminders db = new DBHandlerReminders(context2, "" + Reminder.get(position).getReminderId() + "");
            db.removeReminder(Reminder.get(position).getReminderTitle());
            Reminder.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, Reminder.size());

        });


    }


    /**
     * This method is used to get the number of items in the recycler view
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return Reminder.size();
    }
}
