package com.example.remindme;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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


    //return to main activity
    public void ReturnToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}