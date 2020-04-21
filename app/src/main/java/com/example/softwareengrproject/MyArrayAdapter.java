package com.example.softwareengrproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class MyArrayAdapter extends ArrayAdapter<String> {

    String[] coursenames;
    Context cContext;

    public MyArrayAdapter(@NonNull Context context, String[] names)
    {
        super(context, R.layout.listview_item);
        this.coursenames = names;
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
        ViewHolder cViewHolder = new ViewHolder();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) cContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
            cViewHolder.course = (TextView) convertView.findViewById(R.id.textView5);
            convertView.setTag(cViewHolder);
        }
        else
        {
            cViewHolder = (ViewHolder)convertView.getTag();
        }

        cViewHolder.course.setText(coursenames[position]);

        return convertView;
    }

    static class ViewHolder
    {
        TextView course;
    }


}