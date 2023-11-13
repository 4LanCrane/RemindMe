package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    DBHandler db = new DBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new MyAdaptor(getApplicationContext(), db.readCollections()));

        findViewById(R.id.addCollection).setOnClickListener(click -> {
            Intent intent = new Intent(this, CreateNewCollection.class);
            startActivity(intent);
        });

    }








// method that runs when button is clicked and removes a collection
    public void RemoveCollectionTest(View view) {
        DBHandler db = new DBHandler(this);
        db.removeCollection("Test2");
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new MyAdaptor(getApplicationContext(),db.readCollections()));
    }

    // method that runs when  button is clicked and adds a collection
    public void AddCollectionTest(View view) {
        DBHandler db = new DBHandler(this);
        db.addNewCollection("Test2");
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new MyAdaptor(getApplicationContext(),db.readCollections()));
    }


    // on resume method that runs when the app is resumed
    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new MyAdaptor(getApplicationContext(),db.readCollections()));
    }

}