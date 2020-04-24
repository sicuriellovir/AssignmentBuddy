package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PrivacyNotif extends AppCompatActivity {

     EditText oldPssword;
     EditText newPass;
     EditText confirmPass;
     Button submitButton;

     String user;
     String pass;
     String checkOld, checkNew, checkCon;

     AcctDB sqHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_notif);


        Button Back = findViewById((R.id.PrivacyBack));
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        oldPssword = findViewById(R.id.editText);
        newPass = findViewById(R.id.editText2);
        confirmPass = findViewById(R.id.editText3);
        submitButton = findViewById(R.id.SubmitButton);

        checkOld = oldPssword.getText().toString();
        checkNew = newPass.getText().toString();
        checkCon = confirmPass.getText().toString();



        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!checkCon.equals(newPass))
                    Toast.makeText(PrivacyNotif.this, "Passwords do not match", Toast.LENGTH_SHORT).show();

                else if(!pass.equals(oldPssword))
                    Toast.makeText(PrivacyNotif.this, "Incorrect Password", Toast.LENGTH_SHORT).show();

                else if(checkCon.equals(checkNew.equals(checkOld)))
                    Toast.makeText(PrivacyNotif.this, "New password cannot be the old password", Toast.LENGTH_SHORT).show();

                else
                {
                    sqHelper = new AcctDB();
                    sqHelper.changePass(checkNew,user);
                }


            }
        });

    }

    public void goBack(){
        Intent intent = new Intent(this, SettingsActivityPage.class);
        startActivity(intent);
    }
}
