package com.example.softwareengrproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//File Description: Creates activity page for the home screen page
//Design/interface of this activity - stored in: activity_homescreenscreen.xml
public class HomeScreen extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public void openActivity2() {
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }
}
