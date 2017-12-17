package com.example.girish.sqlitedemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.girish.sqlitedemo.data.HabbitContract;
import com.example.girish.sqlitedemo.data.HabbitDbHelper;

public class MainActivity extends AppCompatActivity {
EditText nameText;
EditText genderText;
Button   saveButton;
Button   displayButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText=(EditText) findViewById(R.id.editName);
        genderText=(EditText)findViewById(R.id.editGender);
        saveButton = (Button)findViewById(R.id.saveButton);
        displayButton=(Button)findViewById(R.id.displayButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            insertPerson();
        }
    });
      displayButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent i= new Intent(MainActivity.this,DisplayActivity.class);
            startActivity(i);
          }
      });
    }
    private void insertPerson() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = nameText.getText().toString().trim();
        String genderString = genderText.getText().toString().trim();


        // Create database helper
        HabbitDbHelper mDbHelper = new HabbitDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and person attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(HabbitContract.HabbitEntry.COLUMN_PERSON_NAME, nameString);

        values.put(HabbitContract.HabbitEntry.COLUMN_PERSON_GENDER,genderString );


        // Insert a new row for person in the database, returning the ID of that new row.
        long newRowId = db.insert(HabbitContract.HabbitEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving ", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Habbit saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }




}
