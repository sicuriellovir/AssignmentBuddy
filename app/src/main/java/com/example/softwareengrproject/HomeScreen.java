package com.example.softwareengrproject;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.softwareengrproject.LoginScreen;
import com.example.softwareengrproject.R;
import com.example.softwareengrproject.RegisterScreen;

import com.example.softwareengrproject.LoginScreen;
import com.example.softwareengrproject.R;
import com.example.softwareengrproject.RegisterScreen;

import com.example.softwareengrproject.LoginScreen;
import com.example.softwareengrproject.R;
import com.example.softwareengrproject.RegisterScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.softwareengrproject.LoginScreen;
import com.example.softwareengrproject.R;
import com.example.softwareengrproject.RegisterScreen;

import androidx.appcompat.app.AppCompatActivity;

//File Description: Creates activity page for the home screen page
//Design/interface of this activity - stored in: activity_homescreenscreen.xml
public class HomeScreen extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);


        Button button1 = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(HomeScreen.this, LoginScreen.class);
                startActivity(int1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(HomeScreen.this, RegisterScreen.class);
                startActivity(int2);
            }
        });


    }

}

