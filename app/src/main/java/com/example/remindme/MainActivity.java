package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);

        DBHandler db = new DBHandler(this);




        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new MyAdaptor(getApplicationContext(),db.readCollections()));
}



// method that runs when floating action button is clicked and removes a collection
    public void RemoveCollectionTest(View view) {
        DBHandler db = new DBHandler(this);
        db.removeCollection("Test2");
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new MyAdaptor(getApplicationContext(),db.readCollections()));
    }

    // method that runs when floating action button is clicked and adds a collection
    public void AddCollectionTest(View view) {
        DBHandler db = new DBHandler(this);
        db.addNewCollection("Test2");
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new MyAdaptor(getApplicationContext(),db.readCollections()));
    }

}