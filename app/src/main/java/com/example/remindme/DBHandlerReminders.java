package com.example.remindme;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandlerReminders extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static  String DB_NAME;
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "Reminders";
    private static final String ID_COL = "id";
    private static final String Reminer_Title_COL = "Reminder_Title";
    private static  String Reminder_Time = "Reminder_Time";

    private static  String Reminder_Date = "Reminder_Date";


    public DBHandlerReminders(Context context,String id) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_NAME = id;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Reminer_Title_COL + " TEXT,"
                + Reminder_Time + " TEXT,"
                + Reminder_Date + " TEXT)";

        db.execSQL(query);
    }


    public void addNewReminder(String Title,String ReminderTime,String ReminderDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Reminer_Title_COL, Title);
        values.put(Reminder_Time,ReminderTime);
        values.put(Reminder_Date,ReminderDate);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }



    public void removeReminder(String Title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, Reminer_Title_COL + " = ?",
                new String[]{String.valueOf(Title)});
        db.close();
    }

    public ArrayList<Reminder> readReminders() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Reminder> ReminderArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                ReminderArrayList.add(new Reminder(cursor.getInt(0),
                        cursor.getString(1),cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return ReminderArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }






}
