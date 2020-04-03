package com.example.softwareengrproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//File Description: Creates activity page for the home screen page
//Design/interface of this activity - stored in: activity_homescreenscreen.xml
public class HomeScreen extends AppCompatActivity {
    //private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById((R.id.button2));
        Button button3 = findViewById((R.id.button3));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });


    }

    public void openActivity2() {
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, ScreenRegister.class);
        startActivity(intent);
    }

    public void openActivity4() {
        Intent intent = new Intent(this, CalendarScreen.class);
        startActivity(intent);
    }
}
