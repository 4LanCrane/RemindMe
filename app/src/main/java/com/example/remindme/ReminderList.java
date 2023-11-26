package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ReminderList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        int collectionId = getIntent().getExtras().getInt("collectionId");
        DBHandlerReminders db = new DBHandlerReminders(this,""+collectionId+"");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_list);

            RecyclerView recyclerView2 = findViewById(R.id.RecyclerView2);
            recyclerView2.setLayoutManager(new LinearLayoutManager(this));
            recyclerView2.setAdapter(new MyAdaptorForReminders(getApplicationContext(),db.readReminders()));

            Button addReminder = findViewById(R.id.buttonTest);
            addReminder.setOnClickListener(click -> {
                Intent intent = new Intent(this, AddNewReminder.class);
                intent.putExtra("collectionId", collectionId);
                startActivity(intent);
            });
        };


    //method so when you click on a reminder it removes it  from the list
    public void RemoveReminder(View view, String reminderName) {
        int collectionId = getIntent().getExtras().getInt("collectionId");
        DBHandlerReminders db = new DBHandlerReminders(this,""+collectionId+"");
        db.removeReminder(reminderName);
        RecyclerView recyclerView = findViewById(R.id.RecyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdaptorForReminders(getApplicationContext(),db.readReminders()));
    }



    // method that runs when  button is clicked and adds a reminder
    public void AddReminderTest(View view) {

        int collectionId = getIntent().getExtras().getInt("collectionId");
        DBHandlerReminders db = new DBHandlerReminders(this,""+collectionId+"");
        db.addNewReminder("Test2","Test2","Test2");
        RecyclerView recyclerView = findViewById(R.id.RecyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdaptorForReminders(getApplicationContext(),db.readReminders()));
    }


    //method to return to the main activity
    public void ReturnToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


// on resume method
    @Override
    protected void onResume() {
        super.onResume();
        int collectionId = getIntent().getExtras().getInt("collectionId");
        DBHandlerReminders db = new DBHandlerReminders(this,""+collectionId+"");
        RecyclerView recyclerView = findViewById(R.id.RecyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdaptorForReminders(getApplicationContext(),db.readReminders()));
    }



    

}