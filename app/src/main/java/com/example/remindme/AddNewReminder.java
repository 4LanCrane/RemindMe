package com.example.remindme;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import java.util.Calendar;
import java.util.Random;

public class AddNewReminder extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    private NotificationManagerCompat notificationManagerCompat; // notification manager

    private static final String TAG = "AddNewReminder"; // tag for debugging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_reminder);


        TextView ReminderTitle = findViewById(R.id.addReminderTitle);
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);
        txtDate = (EditText) findViewById(R.id.in_date);
        txtTime = (EditText) findViewById(R.id.in_time);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        notificationManagerCompat = NotificationManagerCompat.from(this);

    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();// get the current date
            mYear = c.get(Calendar.YEAR);// get the current year
            mMonth = c.get(Calendar.MONTH);// get the current month
            mDay = c.get(Calendar.DAY_OF_MONTH);// get the current day

            // create a date picker dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {


                        @Override // method that is run when the date picker is set and sets the date in the text box adds missing 0s
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            if (dayOfMonth <= 9 && month <= 9) {
                                txtDate.setText("0" + dayOfMonth + "-" + "0" + (month + 1) + "-" + year);
                            }
                           else if (dayOfMonth <= 9) {
                                txtDate.setText("0" + dayOfMonth + "-" + (month + 1) + "-" + year);
                            } else if (month <= 9) {
                                txtDate.setText(dayOfMonth + "-" + "0" + (month + 1) + "-" + year);
                            } else {
                                txtDate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                            }
                        }
                    }, mYear, mMonth, mDay);


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

                        @Override // method that is run when the time picker is set and sets the time in the text box adds missing 0s
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                             if (hourOfDay <= 9 && minute <= 9) {
                                txtTime.setText("0" + hourOfDay + ":" + "0" + minute);
                             } else if (hourOfDay <= 9) {
                                txtTime.setText("0" + hourOfDay + ":" + minute);
                            } else if (minute <= 9) {
                                txtTime.setText(hourOfDay + ":" + "0" + minute);

                            } else {
                                txtTime.setText(hourOfDay + ":" + minute);
                            }


                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();// show the time picker dialog
        }
    }

    // method to take all inputs and store in reminders database
    public void AddReminder(View view) {
        int collectionId = getIntent().getExtras().getInt("collectionId");
        DBHandlerReminders db = new DBHandlerReminders(this, "" + collectionId + "");


        // check if the title, date and time are empty and set them to default values
        EditText ReminderTitle = findViewById(R.id.addReminderTitle);
        if (ReminderTitle.getText().toString().equals("")) {
            ReminderTitle.setText("New Reminder");
        }

        EditText ReminderDate = findViewById(R.id.in_date);
        if (ReminderDate.getText().toString().equals("")) {
            ReminderDate.setText("01-01-2021");
        }

        EditText ReminderTime = findViewById(R.id.in_time);
        if (ReminderTime.getText().toString().equals("")) {
            ReminderTime.setText("00:00");
        }

        // create a calendar object and set the date and time to the date and time in the text boxes
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(ReminderDate.getText().toString().substring(6)));
        calendar.set(Calendar.MONTH, Integer.parseInt(ReminderDate.getText().toString().substring(3, 5)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ReminderDate.getText().toString().substring(0, 2)));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ReminderTime.getText().toString().substring(0, 2)));
        calendar.set(Calendar.MINUTE, Integer.parseInt(ReminderTime.getText().toString().substring(3)));
        calendar.set(Calendar.SECOND, 0);
        setAlarm(calendar.getTimeInMillis());

        // add the reminder to the database
        db.addNewReminder(ReminderTitle.getText().toString(), ReminderTime.getText().toString(), ReminderDate.getText().toString());
        // go back to reminder list passing the collection id
        Intent intent = new Intent(this, ReminderList.class);
        intent.putExtra("collectionId", collectionId);
        startActivity(intent);

    }




    /**
     * Sets a new alarm.
     *
     * @param timeInMillis The time in milliseconds when the alarm should go off.
     */
    @SuppressLint("ScheduleExactAlarm")
    private void setAlarm(long timeInMillis) {

        Random alarmid = new Random();
        Log.d(TAG, "setAlarm id is: " + alarmid);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE); // get the alarm manager
        Intent intent = new Intent(this, Alarm.class); // intent to start the receiver
        EditText reminder = findViewById(R.id.addReminderTitle); // get the reminder title
        String reminderTitle = reminder.getText().toString(); // convert the reminder title to a string
        intent.putExtra("reminderTitle", reminderTitle); // add the reminder title to the intent
        Log.d(TAG, "add new has title as : " + intent.getExtras().toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, alarmid.nextInt(), intent, PendingIntent.FLAG_IMMUTABLE);// create a pending intent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// check if the version is marshmallow or above as each version has different methods
            alarmManager.setAlarmClock(
                    new AlarmManager.AlarmClockInfo(timeInMillis, pendingIntent), pendingIntent);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
        }

    }


    //method to return to the reminder list
    public void ReturnToReminderList(View view) {
        finish();
    }
}