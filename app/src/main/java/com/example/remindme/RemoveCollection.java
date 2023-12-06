package com.example.remindme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class RemoveCollection extends AppCompatActivity {

    /**
     * This method is called when the activity is created
     * It adds a click listener to the "Submit" button
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_collection);


        EditText titleInput = findViewById(R.id.editTextText);


        // Add click listener to create button

        findViewById(R.id.submitbutton).setOnClickListener(click -> {


            // Get DBHandler instance
            DBHandler dbHandler = new DBHandler(this);
            // Add new recipe
            dbHandler.removeCollection(titleInput.getText().toString());


            // Go back to main activity
            finish();
        });}

    /**
     * This method is called when the user clicks the "Return to Main Activity" button
     * @param view
     */
    public void ReturnToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}