package com.example.softwareengrproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class GradesArrayAdapter extends ArrayAdapter<String> {

    String[] gradenames;    //name of a grade
    String[] gradedates;    //due date of grade
    String[] grades;        //actual grade, 80, 90, 100, etc
    Context gContext;

    public GradesArrayAdapter(@NonNull Context context, String[] names, String[] dates, String[] g)
    {
        super(context, R.layout.gradeslist_item);
        this.gradenames = names;
        this.gradedates = dates;
        this.grades = g;
        this.gContext = context;
    }

    @Override
    public int getCount()
    {
        return gradenames.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder cViewHolder = new ViewHolder();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) gContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gradeslist_item, parent, false);
            cViewHolder.gradeN = (TextView) convertView.findViewById(R.id.gradeNameView);
            cViewHolder.gradeD = (TextView) convertView.findViewById(R.id.dueDateView);
            cViewHolder.grade = (TextView) convertView.findViewById(R.id.gradeView);
            convertView.setTag(cViewHolder);
        }
        else
        {
            cViewHolder = (ViewHolder)convertView.getTag();
        }

        cViewHolder.gradeN.setText(gradenames[position]);
        cViewHolder.gradeD.setText(gradedates[position]);
        cViewHolder.grade.setText(grades[position]);

        return convertView;
    }

    static class ViewHolder
    {
        TextView gradeN;    //hold name of grade/assignment
        TextView gradeD;    //holds due date of grade
        TextView grade;     //hold actual grade
    }


}