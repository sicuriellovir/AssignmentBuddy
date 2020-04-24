package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PrivacyNotif extends AppCompatActivity {

    private EditText password;
    private Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_notif);


        //Note: [when merging] Changed from Button to ImageButton
        ImageButton Back = findViewById((R.id.PrivacyBack));
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        submitButton = (Button)findViewById(R.id.SubmitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });

    }

    public void goBack(){
        Intent intent = new Intent(this, SettingsActivityPage.class);
        startActivity(intent);
    }
}
