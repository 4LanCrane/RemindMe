package com.example.remindme;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * This class is used to handle the database for the reminders
 * it is used to add,remove and read reminders from the database
 */
public class DBHandlerReminders extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static String DB_NAME;
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "Reminders";
    private static final String ID_COL = "id";
    private static final String Reminder_Title_COL = "Reminder_Title";
    private static String Reminder_Time = "Reminder_Time";

    private static String Reminder_Date = "Reminder_Date";


    /**
     * constructor for the database handler
     *
     * @param context
     */
    public DBHandlerReminders(Context context, String id) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_NAME = id;
    }


    /**
     * This method is used to create the database
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Reminder_Title_COL + " TEXT,"
                + Reminder_Time + " TEXT,"
                + Reminder_Date + " TEXT)";

        db.execSQL(query);
    }


    /**
     * This method is used to add a new reminder to the database
     *
     * @param Title
     */
    public void addNewReminder(String Title, String ReminderTime, String ReminderDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Reminder_Title_COL, Title);
        values.put(Reminder_Time, ReminderTime);
        values.put(Reminder_Date, ReminderDate);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //get the size of the database but never decrement it


    /**
     * This method is used to remove a reminder from the database
     *
     * @param Title
     */
    public void removeReminder(String Title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, Reminder_Title_COL + " = ?",
                new String[]{String.valueOf(Title)});
        db.close();
    }

    /**
     * This method is used to read the reminders from the database
     */
    public ArrayList<Reminder> readReminders() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Reminder> ReminderArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                ReminderArrayList.add(new Reminder(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return ReminderArrayList;
    }


    /**
     * This method is used to upgrade the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
