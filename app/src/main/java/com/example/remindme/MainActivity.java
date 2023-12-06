package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new MyAdaptor(getApplicationContext(), db.readCollections()));

        findViewById(R.id.addCollection).setOnClickListener(click -> {
            Log.d(TAG, "User clicked Add Collection button ");
            Intent intent = new Intent(this, CreateNewCollection.class);
            startActivity(intent);

        });

    }


    /**
     * This method is called when the user clicks the "Remove Collection" button
     * It takes the user to the  MainActivity
     *
     * @param view
     */
    public void RemoveCollection(View view) {
        Log.d(TAG, "User clicked Remove Collection button");
        Intent intent = new Intent(this, RemoveCollection.class);
        startActivity(intent);
    }


    /**
     * This method is called on resume of the activity and updates the recycler view
     */
    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new MyAdaptor(getApplicationContext(), db.readCollections()));
    }

}