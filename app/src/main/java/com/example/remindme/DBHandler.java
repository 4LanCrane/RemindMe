package com.example.remindme;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "Collectiondb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Collections";
    private static final String ID_COL = "id";
    private static final String Title_COL = "Title";


    /**
     * constructor for the database handler
     * @param context
     */
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * This method is used to create the database
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Title_COL + " TEXT)";
        db.execSQL(query);
    }


    /**
     * This method is used to add a new collection to the database
     * @param Title
     */
    public void addNewCollection(String Title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Title_COL, Title);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    /**
     * This method is used to remove a collection from the database
     * @param Title
     */
    public void removeCollection(String Title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, Title_COL + " = ?",
                new String[]{String.valueOf(Title)});
        db.close();
    }

    /**
     * This method is used to read the collections from the database
     *
     */
    public ArrayList<Collections> readCollections() {
        ArrayList<Collections> collectionsArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                collectionsArrayList.add(new Collections(cursor.getString(1), cursor.getInt(0)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return collectionsArrayList;
    }


    /**
     * This method is used to upgrade the database
     * @param db
     * @param PreviousVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int PreviousVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
