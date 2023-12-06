package com.example.remindme;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTests {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.remindme", appContext.getPackageName());
    }


    @Test
    public void testAddNewCollection() {
        //test if the collection is added to the database
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBHandler dbHandler = new DBHandler(context);
        dbHandler.addNewCollection("test");
        assertEquals("test", dbHandler.readCollections().get(0).title);
    }

    @Test
    public void testRemoveCollection() {
        //test if the collection is removed from the database
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBHandler dbHandler = new DBHandler(context);
        dbHandler.addNewCollection("test");
        dbHandler.removeCollection("test");
        assertEquals(0, dbHandler.readCollections().size());
    }

}