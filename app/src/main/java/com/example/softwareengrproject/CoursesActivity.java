package com.example.softwareengrproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CoursesActivity extends AppCompatActivity {

    ListView courseList;
    String[] classlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        courseList = (ListView) findViewById(R.id.listview);
    }

    public class CoursesAdapter extends ArrayAdapter<String> {

        String[] coursenames;
        Context cContext;

        public CoursesAdapter(@NonNull Context context, String[] n) {
            super(context, R.layout.listview_item);
            this.coursenames = n;
            this.cContext = context;
        }
        @Override
        public int getCount()
        {
            return coursenames.length;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater) cContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
            TextView course = (TextView)convertView.findViewById(R.id.textView5);

            course.setText("c");
            return super.getView(position, convertView, parent);

        }

    }
}
