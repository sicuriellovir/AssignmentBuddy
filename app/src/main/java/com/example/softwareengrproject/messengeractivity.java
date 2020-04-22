package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class messengeractivity extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messengeractivity);

        editText = (EditText) findViewById(R.id.messengerEditText);
    }

    public void sendMessage(View view) {
        String message = editText.getText().toString();

        // Send in the chat
        if (message.length() > 0) {
            editText.getText().clear();
        }
    }
}
