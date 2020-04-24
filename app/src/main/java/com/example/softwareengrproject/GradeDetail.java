package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GradeDetail extends AppCompatActivity {

    TextView gradeTitle, averageText, scoreText;
    String userName, courseName, assignmentName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_detail);

        gradeTitle = (TextView) findViewById(R.id.gradeNameView);
        averageText = (TextView) findViewById(R.id.averageGradeView);
        scoreText = (TextView) findViewById(R.id.score_view);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            gradeTitle.setText(bundle.getString("gradeName"));
            userName = bundle.getString("username");
            courseName = bundle.getString("courseName");
            assignmentName = bundle.getString("gradeName");

            float average = calculate_average(assignmentName,courseName);

            // Update number value
            scoreText.setText(Float.toString(average));
        }

        Button buttonBack2Grade = findViewById(R.id.buttonBackToGradeSelect);

        buttonBack2Grade.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToCourseDetail();
            }
        });
    }

    public void returnToCourseDetail()
    {
        Intent intent = new Intent(this, CourseDetail.class);
        intent.putExtra("username", userName);
        intent.putExtra("className", courseName);
        startActivity(intent);
    }

    float calculate_average(String assignmentName, String courseName)
    {
        // Query table for all submitted grades and see the average
        Cursor mCursor;
        float average=0;

        String[] mProjection = new String[]{ AcctDB.COLUMN_GRADE };
        String mSelection = AcctDB.COLUMN_ASSIGNMENT + "= ? AND " + AcctDB.COLUMN_COURSE_ID + "= ?";
        String[] mSelectionArgs = new String[]{assignmentName, courseName};
        mCursor = getContentResolver().query(AcctDB.CONTENT_URI_GRADES, mProjection, mSelection, mSelectionArgs, null);

        if( mCursor != null)
        {
            mCursor.moveToFirst();
            for(int i = 0; i < mCursor.getCount(); i++)
            {
                average += mCursor.getInt(0);
                mCursor.moveToNext();
            }

            average /= mCursor.getCount();
        }

        return average;
    }
}
