package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PrivacyNotif extends AppCompatActivity {

    private EditText curr_password, new_pass, confirm_pass;
    String userName;
    private Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_notif);

        Bundle extras = getIntent().getExtras();

        if ( extras != null )
        {
            userName = extras.getString("username");
            Log.d("Bundle user", userName);
        }

        curr_password = findViewById(R.id.etcurrpass);
        new_pass = findViewById(R.id.etnewpass);
        confirm_pass = findViewById(R.id.etconfirm);

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

                String current_pass_str, new_pass_str, confirm_pass_str;
                current_pass_str = curr_password.getText().toString();
                new_pass_str = new_pass.getText().toString();
                confirm_pass_str = confirm_pass.getText().toString();

                Cursor mCursor;
                // Query Account table



                String[] mProjection = new String[]{ AcctDB.COLUMN_ACCT_ID, AcctDB.COLUMN_PASSWD };
                //Cursor for the id column in content provider
                String mSelection = AcctDB.COLUMN_ACCT_ID + "= ? AND " + AcctDB.COLUMN_PASSWD + "= ?";

                String[] mSelectionArgs = new String[]{userName, current_pass_str };

                mCursor = getContentResolver().query(AcctDB.CONTENT_URI_ACCT, mProjection, mSelection, mSelectionArgs, null);

                if( mCursor != null )
                {
                    if( new_pass_str.equals(confirm_pass_str) )
                    {
                        // Make sure passwords match
                        // Update entry's password

                        ContentValues cv = new ContentValues();
                        cv.put(AcctDB.COLUMN_PASSWD, new_pass_str);

                        getContentResolver().update(AcctDB.CONTENT_URI_ACCT, cv, mSelection, mSelectionArgs);

                        goBack();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Passwords do not match.", Toast.LENGTH_LONG).show();
                    }
                }

                else{
                    Toast.makeText(getApplicationContext(),"Incorrect Password.", Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    public void goBack(){
        Intent intent = new Intent(this, SettingsActivityPage.class);
        intent.putExtra("username", userName);
        startActivity(intent);
    }
}
