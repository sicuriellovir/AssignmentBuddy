package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddAssignment extends AppCompatActivity {

    String userName;
    EditText editText,etMonth, etDay, etGrade, stuName;
    String assnName, stuUser;
    int month, day, grade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

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
                String course = editText.getText().toString();
                month = Integer.parseInt(etMonth.getText().toString());
                day = Integer.parseInt(etDay.getText().toString());
                stuUser = stuName.getText().toString();
                add(course, month, day, grade, stuUser);
            }
        });
    }

    public void add(String s, int m, int d, int g, String u)
    {
        Uri uri;
        //add assignment to grades database
        ContentValues cv = new ContentValues();

        cv.put(AcctDB.COLUMN_GRADES_USER_ID, u);
        cv.put(AcctDB.COLUMN_ASSIGNMENT, s);
        cv.put(AcctDB.COLUMN_ASSIGNMENT_DUE_MONTH, m);
        cv.put(AcctDB.COLUMN_ASSIGNMENT_DUE_DAY, d);
        cv.put(AcctDB.COLUMN_GRADE, g);

        uri = getContentResolver().insert(AcctDB.CONTENT_URI_GRADES, cv);

    }

    public void returnToCourseDetail()
    {
        Intent intent = new Intent(this, CourseDetail.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }

}
