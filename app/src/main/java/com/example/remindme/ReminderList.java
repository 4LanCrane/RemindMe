package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ReminderList extends AppCompatActivity {

    DBHandlerReminders db = new DBHandlerReminders(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_list);
        RecyclerView recyclerView = findViewById(R.id.RecyclerView2);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new MyAdaptorForReminders(getApplicationContext(), db.readReminders()));
        //set the text view to the value of id


        int collectionId = getIntent().getExtras().getInt("collectionId");

        //add a reminder to the collection

            RecyclerView recyclerView2 = findViewById(R.id.RecyclerView2);
            recyclerView2.setLayoutManager(new GridLayoutManager(this,2));
            recyclerView2.setAdapter(new MyAdaptorForReminders(getApplicationContext(),db.readReminders()));
        };



    // method that runs when  button is clicked and adds a reminder
    public void AddReminderTest(View view) {
        DBHandlerReminders db = new DBHandlerReminders(this);
        db.addNewReminder("Test2");
        RecyclerView recyclerView = findViewById(R.id.RecyclerView2);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new MyAdaptorForReminders(getApplicationContext(),db.readReminders()));
    }





}