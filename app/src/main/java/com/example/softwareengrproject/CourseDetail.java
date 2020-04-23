package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//course detail displays the grades in a specific class in a listview
public class CourseDetail extends AppCompatActivity
{
    TextView text;
    ListView gradeList;
    // To Store assignments
    String[] gradeNames;

    // To Store due dates
    String[] gradeDates;

    int [] gradeDays;
    int [] gradeMonth;

    // To store grades
    String[] grades;

    String userName;
    String courseName;

    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        text = (TextView) findViewById(R.id.textCourseDetail);
        gradeList = (ListView) findViewById(R.id.gradeslist);

        Bundle bundle = getIntent().getExtras();    //getting extra from Courses Activity
        if(bundle != null)
        {
            courseName = bundle.getString("className");
            userName = bundle.getString("username");
            text.setText(bundle.getString("className"));
        }

        // Query grades table with username and coursename and store assignment names, due dates, grade,
        //Cursor for the id column in content provider
        Cursor mCursor;

        // Get the course id's
        String[] mProjection = new String[]{ AcctDB.COLUMN_ASSIGNMENT, AcctDB.COLUMN_ASSIGNMENT_DUE_MONTH, AcctDB.COLUMN_ASSIGNMENT_DUE_DAY, AcctDB.COLUMN_GRADE };


        String mSelection = AcctDB.COLUMN_GRADES_USER_ID + "= ? AND " + AcctDB.COLUMN_COURSE_ID + "= ?";

        String[] mSelectionArgs = new String[]{userName, courseName};

        mCursor = getContentResolver().query(AcctDB.CONTENT_URI_GRADES, mProjection, mSelection, mSelectionArgs, null);
        try{
            if (mCursor != null)
            {
                Log.d("OnCreate","Cursor not null");
                mCursor.moveToFirst();

                gradeNames = new String[mCursor.getCount()];
                grades = new String[mCursor.getCount()];
                gradeDays = new int[mCursor.getCount()];
                gradeMonth = new int [mCursor.getCount()];

                for (int i = 0; i < gradeNames.length; i++) {
                    gradeNames[i] = mCursor.getString(0);
                    gradeMonth[i] = mCursor.getInt(1);
                    gradeDays[i] = mCursor.getInt(2);
                    grades[i] = Integer.toString(mCursor.getInt(3));
                    Log.d("Assignment name: ", gradeNames[i]);
                    Log.d("Grade", grades[i]);
                    mCursor.moveToNext();

                    // Convert ints in month and days to string for gradeDates
                    gradeDates = new String[mCursor.getCount()];
                    for(int j = 0; j < gradeDates.length; j++)
                    {
                        String entry;
                        entry = Integer.toString(gradeMonth[j]) + "/" + Integer.toString(gradeDays[j]);
                        gradeDates[j] = entry;
                        entry = "";
                    }
                }

                mCursor.close();

            }

            else
            {
                Log.d("Courses: ", "No courses to display");
                Toast.makeText(getApplicationContext(), "No grades to display yet!",Toast.LENGTH_SHORT).show();
            }

        }catch ( SQLException e){
            e.printStackTrace();
        }



        GradesArrayAdapter gradeAdapter = new GradesArrayAdapter(CourseDetail.this, gradeNames, gradeDates, grades);
        gradeList.setAdapter(gradeAdapter);
        gradeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CourseDetail.this, GradeDetail.class);
                intent.putExtra("gradeName", gradeNames[i]);
                intent.putExtra("username", userName);
                intent.putExtra("courseName", courseName);
                startActivity(intent);
            }
        });


        Button buttonBack2Course = findViewById(R.id.buttonBackToCourseSelect);

        buttonBack2Course.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToCourses();
            }
        });

        Button addAssignment = findViewById(R.id.addAssignmentButton);
        addAssignment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddAssignment();
            }
        });
    }

    public void returnToCourses()
    {
        Intent intent = new Intent(this, CoursesActivity.class);
        intent.putExtra("username", userName);
        startActivity(intent);
    }

    public void openAddAssignment()
    {
        Intent intent = new Intent(this, AddAssignment.class);
        startActivity(intent);
    }
}
