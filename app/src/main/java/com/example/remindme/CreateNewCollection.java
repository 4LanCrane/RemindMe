package com.example.remindme;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNewCollection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_collection);


    EditText titleInput = findViewById(R.id.editTextText);



    // Add click listener to create button
    findViewById(R.id.submitbutton).setOnClickListener(click -> {


        // Get DBHandler instance
        DBHandler dbHandler = new DBHandler(this);
        // Add new recipe
        dbHandler.addNewCollection(titleInput.getText().toString());


        // Go back to main activity
        finish();
    });}



}