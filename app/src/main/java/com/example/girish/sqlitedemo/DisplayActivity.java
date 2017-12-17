package com.example.girish.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.girish.sqlitedemo.data.HabbitContract;
import com.example.girish.sqlitedemo.data.HabbitDbHelper;

public class DisplayActivity extends AppCompatActivity {
    /**
     * Database helper that will provide us access to the database
     */
    private HabbitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabbitDbHelper(this);
        displayDatabaseInfo();

    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the habbit database.
     */
    private void displayDatabaseInfo() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabbitContract.HabbitEntry._ID,
                HabbitContract.HabbitEntry.COLUMN_PERSON_NAME,

                HabbitContract.HabbitEntry.COLUMN_PERSON_GENDER,

        };
        Cursor cursor = db.query(HabbitContract.HabbitEntry.TABLE_NAME, projection, null, null, null, null, null);
        TextView displayView = (TextView) findViewById(R.id.text_view_pet);


        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // habbit table in the database).
            // Create a header in the Text View that looks like this:
            //
            // The habbit table contains <number of rows in Cursor> .
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The Habbit table contains " + cursor.getCount() + " habbits of people.\n\n");
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry.COLUMN_PERSON_NAME);
            int genderColumnIndex = cursor.getColumnIndex(HabbitContract.HabbitEntry.COLUMN_PERSON_GENDER);


            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentGender = cursor.getString(genderColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +

                        currentGender  ));
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

}
