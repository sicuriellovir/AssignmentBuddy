package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScreenRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_register);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById((R.id.button2));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity5();
            }
        });
    }

    public void openActivity4()
    {
//        Intent intent = new Intent(this, .class);
//        startActivity(intent);
    }

    public void openActivity5()
    {
//        Intent intent = new Intent(this, .class);
//        startActivity(intent);
    }
}
