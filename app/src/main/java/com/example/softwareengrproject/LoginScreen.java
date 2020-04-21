package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//File Description: Creates activity page for Login screen
//Design/interface of this activity - stored in: activity_loginscreen.xmlscreen.xml
public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);

        Button Back = findViewById((R.id.BackButtonSettings));
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        Button next = findViewById((R.id.button10));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }

    public void goBack(){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    public void next(){
        Intent intent = new Intent(this,SettingsActivityPage.class);
        startActivity(intent);
    }
}
