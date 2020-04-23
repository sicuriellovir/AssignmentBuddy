package com.example.softwareengrproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CoursesActivity extends AppCompatActivity {

    ListView courseList;
    String[] classNames = {"Physics", "COP 3252", "Comp Org", "Spanish", "Calculus 2"};
    static int SIZE = 5;   //size of classNames array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        courseList = (ListView) findViewById(R.id.listview);
        MyArrayAdapter myAdapter = new MyArrayAdapter(CoursesActivity.this, classNames);
        courseList.setAdapter(myAdapter);
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CoursesActivity.this, CourseDetail.class);
                intent.putExtra("className", classNames[i]);
                startActivity(intent);
            }
        });

        Button buttonBack2Acc = findViewById(R.id.buttonBackToAccScrn);
        buttonBack2Acc.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToAccCourse();
            }
        });

        Button addCourse = findViewById(R.id.addCourseButton);
        addCourse.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCourse();
            }
        });
    }

    public void returnToAccCourse()
    {
        Intent intent = new Intent(this, AccountScreen.class);
        startActivity(intent);
    }

    public void openAddCourse()
    {
        Intent intent = new Intent(this, AddCourse.class);
        startActivity(intent);
    }

    public void addCourse(String s) {
        String[] temp = new String[++SIZE]; //will be new storage of classes
        for (int i = 0; i < SIZE - 1; i++) {
            temp[i] = classNames[i];
        }
        classNames = temp;  //set classNames to the new String array with added class


        MyArrayAdapter myAdapter = new MyArrayAdapter(CoursesActivity.this, classNames);
        courseList.setAdapter(myAdapter);
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CoursesActivity.this, CourseDetail.class);
                intent.putExtra("className", classNames[i]);
                startActivity(intent);
            }
        });
    }

}
