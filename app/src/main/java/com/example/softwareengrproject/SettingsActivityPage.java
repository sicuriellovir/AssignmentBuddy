package com.example.softwareengrproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivityPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
       /* getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar(); */
       // if (actionBar != null) {
         //   actionBar.setDisplayHomeAsUpEnabled(true);

            Button Notif = findViewById((R.id.buttonNotif));
            Notif.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  openNotif();
                }
            });

            Button Back = findViewById((R.id.BackButtonSettings));
            Back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goBack();
                }
            });

            Button PrivMessage = findViewById((R.id.button13));
            PrivMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPrivMessage();
                }
            });

            Button Pass = findViewById((R.id.buttonPrivacy));
            Pass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   passChange();
                }
            });

            Button logOut = findViewById((R.id.buttonLogOut));
            logOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logOut();
                }
            });





        }


  /*  public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    } */

    public void goBack() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }


    public void openNotif() {
        Intent intent = new Intent(this, NotificationsScreen.class);
        startActivity(intent);
    }

    public void openPrivMessage() {
        Intent intent = new Intent(this, PrivacyMessages.class);
        startActivity(intent);
    }

    public void passChange() {
        Intent intent = new Intent(this, PrivacyNotif.class);
        startActivity(intent);
    }

    public void logOut() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}




