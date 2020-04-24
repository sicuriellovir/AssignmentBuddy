package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddAssignment extends AppCompatActivity {

    String userName, coursename;
    EditText editText,etMonth, etDay, etGrade, stuName;
    String assnName, stuUser;
    int month, day, grade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        Bundle extras = getIntent().getExtras();

        if ( extras != null ) {
            Log.d("AddAssignment","In here");
            userName = extras.getString("username");
            coursename = extras.getString("className");
            Log.d("USER", userName);
        }

        editText = findViewById(R.id.editTextAssignment);
        etMonth = findViewById(R.id.editTextDueMonth);
        etDay = findViewById(R.id.editTextDueDay);
        etGrade = findViewById(R.id.editTextGrade);
        stuName = findViewById(R.id.editTextStudent);

        Button back2Gradelist = findViewById(R.id.buttonBackToGradelist);
        back2Gradelist.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToCourseDetail();
            }
        });

        Button add = findViewById(R.id.addButton2);
        add.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String assignment = editText.getText().toString();
                month = Integer.parseInt(etMonth.getText().toString());
                day = Integer.parseInt(etDay.getText().toString());
                grade = Integer.parseInt(etGrade.getText().toString());
                stuUser = stuName.getText().toString();
                add(assignment, month, day, grade, stuUser, coursename);
            }
        });
    }

    public void add(String assignment, int m, int d, int g, String u, String course)
    {
        Uri uri;
        //add assignment to grades database
        ContentValues cv = new ContentValues();

        cv.put(AcctDB.COLUMN_GRADES_USER_ID, u);
        Log.d("U", u);
        cv.put(AcctDB.COLUMN_ASSIGNMENT, assignment);
        cv.put(AcctDB.COLUMN_COURSE_ID, coursename);
        Log.d("S", assignment);
        cv.put(AcctDB.COLUMN_ASSIGNMENT_DUE_MONTH, m);
        Log.d("M", Integer.toString(m));
        cv.put(AcctDB.COLUMN_ASSIGNMENT_DUE_DAY, d);
        Log.d("D", Integer.toString(d));
        cv.put(AcctDB.COLUMN_GRADE, g);
        Log.d("G", Integer.toString(g));

        uri = getContentResolver().insert(AcctDB.CONTENT_URI_GRADES, cv);

        returnToCourseDetail();

    }

    public void returnToCourseDetail()
    {
        Intent intent = new Intent(this, CourseDetail.class);
        intent.putExtra("username", userName);
        intent.putExtra("className", coursename);
        startActivity(intent);
    }

}
