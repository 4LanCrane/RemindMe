package com.example.remindme;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.time.Month;
import java.util.Calendar;

public class AddNewReminder extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    private NotificationManagerCompat notificationManagerCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_reminder);


        TextView ReminderTitle = findViewById(R.id.addReminderTitle);
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        notificationManagerCompat = NotificationManagerCompat.from(this);

    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


//            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
//                    (view, year, monthOfYear, dayOfMonth) -> {txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                    }, mYear, mMonth, mDay);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            if(dayOfMonth<=9){
                                txtDate.setText("0"+dayOfMonth+"-"+(month+1)+"-"+year);
                            }else{txtDate.setText(dayOfMonth + "-" + (month +1) + "-" + year);}
                        }
                    },mYear,mMonth,mDay);



            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            if(hourOfDay <=9){
                                txtTime.setText("0"+hourOfDay + ":" + minute);
                            }else{txtTime.setText(hourOfDay + ":" + minute);}

                            if(minute <=9){
                                txtTime.setText(hourOfDay + ":" + "0"+minute);}
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        }}

   // method to take all inputs and store in reminders database
    public void AddReminder(View view) {
        int collectionId = getIntent().getExtras().getInt("collectionId");
        DBHandlerReminders db = new DBHandlerReminders(this,""+collectionId+"");

        EditText ReminderTitle = findViewById(R.id.addReminderTitle);
        if(ReminderTitle.getText().toString().equals("")){
            ReminderTitle.setText("New Reminder");
        }

        EditText ReminderDate = findViewById(R.id.in_date);
        if(ReminderDate.getText().toString().equals("")){
            ReminderDate.setText("01-01-2021");
        }

        EditText ReminderTime = findViewById(R.id.in_time);
        if(ReminderTime.getText().toString().equals("")){
            ReminderTime.setText("00:00");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(ReminderDate.getText().toString().substring(6)));
        calendar.set(Calendar.MONTH, Integer.parseInt(ReminderDate.getText().toString().substring(3,5))-1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ReminderDate.getText().toString().substring(0,2)));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ReminderTime.getText().toString().substring(0,2)));
        calendar.set(Calendar.MINUTE, Integer.parseInt(ReminderTime.getText().toString().substring(3)));
        calendar.set(Calendar.SECOND, 0);
        setAlarm(calendar.getTimeInMillis());

        db.addNewReminder(ReminderTitle.getText().toString(),ReminderTime.getText().toString(),ReminderDate.getText().toString());
        finish();

    }

    private static final String TAG = "AddNewReminder";
    private int requestCode = 0;private void setAlarm(long timeInMillis) {

        int lenght = (new DBHandlerReminders(this,getIntent().getExtras().getString("collectionId")).readReminders().size());

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Alarm.class);
        EditText reminder = findViewById(R.id.addReminderTitle);
        String reminderTitle = reminder.getText().toString();
        intent.putExtra("reminderTitle", reminderTitle);
        Log.d(TAG, "add new has title as : " + intent.getExtras().toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,lenght,intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,timeInMillis,pendingIntent);

    }





    //method to return to the reminder list
    public void ReturnToReminderList(View view) {
        finish();
    }
}