package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class messengeractivity extends AppCompatActivity {

    private EditText editText;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messengeractivity);

        Bundle extras = getIntent().getExtras();

        if ( extras != null )
        {
            userName = extras.getString("username");
            Log.d("Bundle user", userName);
        }


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
