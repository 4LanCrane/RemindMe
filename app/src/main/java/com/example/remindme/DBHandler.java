package com.example.remindme;

// we have created a new method for reading all the courses.


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

        // creating a constant variables for our database.
        // below variable is for our database name.
        private static final String DB_NAME = "coursedb";

        // below int is our database version
        private static final int DB_VERSION = 1;

        // below variable is for our table name.
        private static final String TABLE_NAME = "Collections";

        // below variable is for our id column.
        private static final String ID_COL = "id";

        // below variable is for our course name column
        private static final String Title_COL = "Title";


        // below variable for our course description column.
        private static final String DESCRIPTION_COL = "description";

        // below variable is for our course tracks column.


        // creating a constructor for our database handler.
        public DBHandler(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        // below method is for creating a database by running a sqlite query
        @Override
        public void onCreate(SQLiteDatabase db) {
            // on below line we are creating
            // an sqlite query and we are
            // setting our column names
            // along with their data types.
            String query = "CREATE TABLE " + TABLE_NAME + " ("
                    + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Title_COL + " TEXT,"
                    + DESCRIPTION_COL + " TEXT)";

            // at last we are calling a exec sql
            // method to execute above sql query
            db.execSQL(query);
        }

        // this method is use to add new course to our sqlite database.
        public void addNewCollection(String Title, String Description) {


            SQLiteDatabase db = this.getWritableDatabase();


            ContentValues values = new ContentValues();


            values.put(Title_COL, Title);
            values.put(DESCRIPTION_COL, Description);


            db.insert(TABLE_NAME, null, values);


            db.close();
        }

        // we have created a new method for reading all the courses.
        public ArrayList<Collections> readCollections() {
            // on below line we are creating a
            // database for reading our database.
            SQLiteDatabase db = this.getReadableDatabase();

            // on below line we are creating a cursor with query to read data from database.
            Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

            // on below line we are creating a new array list.
            ArrayList<Collections> collectionsArrayList = new ArrayList<>();

            // moving our cursor to first position.
            if (cursorCourses.moveToFirst()) {
                do {
                    // on below line we are adding the data from cursor to our array list.
                    collectionsArrayList.add(new Collections(cursorCourses.getString(1),
                            cursorCourses.getString(2)));
                } while (cursorCourses.moveToNext());
                // moving our cursor to next.
            }
            // at last closing our cursor
            // and returning our array list.
            cursorCourses.close();
            return collectionsArrayList;
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // this method is called to check if the table exists already.
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }