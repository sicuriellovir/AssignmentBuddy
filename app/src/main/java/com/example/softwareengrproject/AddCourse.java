package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCourse extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        editText = findViewById(R.id.editTextCourse);   //editText is where they enter the courseID

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
        //add course to database
    }

    public void returnToCourses()
    {
        Intent intent = new Intent(this, CoursesActivity.class);
        startActivity(intent);
    }
}
