package com.example.remindme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This class is used to create a new collection
 * It is called when the user clicks the "Create New Collection" button
 */
public class CreateNewCollection extends AppCompatActivity {

    private static final String TAG = "CreateNewCollection";

    /**
     * This method is called when the activity is created
     * It adds a click listener to the "Submit" button
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_collection);

        // Get input field
        EditText titleInput = findViewById(R.id.editTextText);


        // Add click listener to create button
        findViewById(R.id.submitbutton).setOnClickListener(click -> {
            Log.d(TAG, "User clicked Add submit button ");
            // Get DBHandler instance
            DBHandler dbHandler = new DBHandler(this);
            // Add new collection to database with title from input field
            dbHandler.addNewCollection(titleInput.getText().toString());

            // Go back to main activity
            finish();
        });
    }


    /**
     * This method is called when the user clicks the "Return to Main Activity" button
     * It returns the user to the main activity
     *
     * @param view
     */
    public void ReturnToMainActivity(View view) {
        Log.d(TAG, "User clicked back to Home button ");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}