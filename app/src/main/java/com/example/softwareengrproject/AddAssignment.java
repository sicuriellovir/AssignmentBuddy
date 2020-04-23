package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddAssignment extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        editText = findViewById(R.id.editTextAssignment);

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
                add(course);
            }
        });
    }

    public void add(String s)
    {
        //add assignmemt to database
    }

    public void returnToCourseDetail()
    {
        Intent intent = new Intent(this, CourseDetail.class);
        startActivity(intent);
    }

}
