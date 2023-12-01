package com.example.remindme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
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



//method that is run when the remove collection button is press that takes you to the remove collection activity
    public void RemoveCollection(View view) {
        Intent intent = new Intent(this, RemoveCollection.class);
        startActivity(intent);
    }





// test method that runs when button is clicked and removes a collection
    public void RemoveCollectionTest(View view, String collectionName) {
        DBHandler db = new DBHandler(this);
        db.removeCollection(collectionName);
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

    //method that creates a notification
    @SuppressLint("MissingPermission")
    public  void createNotification(View view) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("test","test", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        final String Channel_ID = "test";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Channel_ID);
        builder.setSmallIcon(R.drawable.test);
        builder.setContentTitle("Reminder");
        builder.setContentText("Alarm! Wake up! Wake up!");

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(1, builder.build());
    }



}