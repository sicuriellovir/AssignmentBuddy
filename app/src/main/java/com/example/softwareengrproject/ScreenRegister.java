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

        Button button = findViewById(R.id.buttonStudent);
        Button button2 = findViewById((R.id.buttonProf));
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
        // Bundle to store account type and send to infoScreen
        String type = "Student";
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        Intent intent = new Intent(ScreenRegister.this, InfoScreenFragment.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void openActivity5()
    {
        // Bundle to store account type and send to infoScreen
        String type = "Professor";
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        Intent intent = new Intent(ScreenRegister.this, InfoScreenFragment.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
