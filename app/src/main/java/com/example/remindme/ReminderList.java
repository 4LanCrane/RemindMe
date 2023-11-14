package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ReminderList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_list);

        //set the text view to the value of id
        TextView textView = findViewById(R.id.textViewlist);

        int collectionId = getIntent().getExtras().getInt("collectionId");


        DBHandler db = new DBHandler(this);


    }





}