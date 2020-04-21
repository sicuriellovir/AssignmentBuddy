package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GradeDetail extends AppCompatActivity {

    TextView gradeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_detail);

        gradeTitle = (TextView) findViewById(R.id.gradeNameView);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            gradeTitle.setText(bundle.getString("gradeName"));
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
        startActivity(intent);
    }
}
