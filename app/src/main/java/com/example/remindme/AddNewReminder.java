package com.example.remindme;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddNewReminder extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

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

    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) -> {txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
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
                    (view, hourOfDay, minute) -> txtTime.setText(hourOfDay + ":" + minute), mHour, mMinute, false);
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


        db.addNewReminder(ReminderTitle.getText().toString(),ReminderTime.getText().toString(),ReminderDate.getText().toString());
        finish();
    }
}