package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCourse extends AppCompatActivity {

    EditText editText;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        editText = findViewById(R.id.editTextCourse);   //editText is where they enter the courseID
        Bundle extras = getIntent().getExtras();
        if ( extras != null )
        {
            userName = extras.getString("username");
            Log.d("Bundle user", userName);
        }

        Button back2Courses = findViewById(R.id.buttonBackToCourses);
        back2Courses.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToCourses();
            }
        });

        Button add = findViewById(R.id.addButton);
        add.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String course = editText.getText().toString();
                add(course);
            }
        });
    }

    public void add(String s)
    {
        Uri uri;

        Cursor mCursor;

        // Query table to see if student already listed course
        String[] mProjection = new String[]{ AcctDB.COLUMN_COURSE_USER_ID, AcctDB.COLUMN_COURSES };
        //Cursor for the id column in content provider
        String mSelection = AcctDB.COLUMN_COURSE_USER_ID + "= ? AND " + AcctDB.COLUMN_COURSES +" = ?";

        s = s.trim();

        String[] mSelectionArgs = new String[]{userName, s};

        Log.v("Try block", "cursor");
        //Query
        mCursor = getContentResolver().query(AcctDB.CONTENT_URI_COURSES, mProjection, mSelection, mSelectionArgs, null);

        if( !mCursor.moveToFirst() ) {
            //add course to database
            ContentValues cv = new ContentValues();
            Log.v("Cursor null", s + " " + userName);
            cv.put(AcctDB.COLUMN_COURSE_USER_ID, userName);
            cv.put(AcctDB.COLUMN_COURSES, s);

            uri = getContentResolver().insert(AcctDB.CONTENT_URI_COURSES, cv);
        }
        else {
            Toast.makeText(getApplicationContext(), "You are already enrolled in this course!", Toast.LENGTH_SHORT).show();
        }
        mCursor.close();

        // Clear submission box
        editText.getText().clear();
    }

    public void returnToCourses()
    {
        Intent intent = new Intent(this, CoursesActivity.class);

        Bundle b = new Bundle();
        b.putString("username",userName);
        intent.putExtras(b);

        startActivity(intent);
    }
}
