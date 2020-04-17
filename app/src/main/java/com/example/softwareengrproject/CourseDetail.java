package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CourseDetail extends AppCompatActivity
{

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        text = (TextView) findViewById(R.id.textCourseDetail);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            text.setText(bundle.getString("className"));
        }

        Button buttonBack2Course = findViewById(R.id.buttonBackToCourseSelect);

        buttonBack2Course.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToCourses();
            }
        });
    }

    public void returnToCourses()
    {
        Intent intent = new Intent(this, CoursesActivity.class);
        startActivity(intent);
    }
}
