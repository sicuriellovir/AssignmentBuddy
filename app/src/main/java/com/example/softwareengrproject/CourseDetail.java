package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
//course detail displays the grades in a specific class in a listview
public class CourseDetail extends AppCompatActivity
{
    TextView text;
    ListView gradeList;
    String[] gradeNames = {"HW 1", "HW 2", "Test 1", "HW 3", "Quiz 1"};
    String[] gradeDates = {"1/07/20", "1/24/20", "2/04/20", "2/24/20", "3/01/20"};
    String[] grades = {"95", "93", "91", "86", "50"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        text = (TextView) findViewById(R.id.textCourseDetail);
        gradeList = (ListView) findViewById(R.id.gradeslist);
        GradesArrayAdapter gradeAdapter = new GradesArrayAdapter(CourseDetail.this, gradeNames, gradeDates, grades);
        gradeList.setAdapter(gradeAdapter);
        gradeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CourseDetail.this, GradeDetail.class);
                intent.putExtra("gradeName", gradeNames[i]);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();    //getting extra from Courses Activity
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
