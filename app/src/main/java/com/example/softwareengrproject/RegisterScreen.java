package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class RegisterScreen extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_register);

        Button button3 = (Button) findViewById(R.id.buttonStudent);
        Button button4 = (Button) findViewById((R.id.buttonProf));
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent int3 = new Intent(RegisterScreen.this, InfoScreenFragment.class);
//                startActivity(int3);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent int4 = new Intent(RegisterScreen.this, LoginScreen.class);
//                startActivity(int4);
            }
        });
    }
}
