package com.example.softwareengrproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
public class CalendarScreen extends AppCompatActivity {

    CustomCalendarView customCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        customCalendarView = (CustomCalendarView)findViewById(R.id.custom_calendar_view);
    }
}
