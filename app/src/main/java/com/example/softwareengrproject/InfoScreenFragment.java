package com.example.softwareengrproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteCursor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InfoScreenFragment extends AppCompatActivity {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private Button backButton, submitButton;
    private EditText fName, lName, passwd, uName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info_screen);

        submitButton = findViewById(R.id.submit_button);
        backButton = findViewById(R.id.back_button);

        fName = findViewById(R.id.first_name_edit_text);
        lName = findViewById(R.id.last_name_edit_text);
        uName = findViewById(R.id.username_edit_text);
        passwd = findViewById(R.id.password_edit_text);

        // When submit clicked, check values and insert into acctDB
        submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View click_view) {
                    // To store submitted values
                    String fNameText = fName.getText().toString();
                    String lNameText = lName.getText().toString();
                    String uNameText = uName.getText().toString();
                    String passwdText = passwd.getText().toString();

                    Bundle bundle = getIntent().getExtras();
                    String acctType = "";
                    if( bundle != null)
                    {
                        acctType = bundle.getString("type");
                    }
                    boolean flag = true;

                    if( fNameText.equals(""))
                    {
                        Toast.makeText(getApplicationContext() , "Please Enter First Name", Toast.LENGTH_SHORT).show();
                        flag = false;
                    }

                    if( lNameText.equals(""))
                    {
                        Toast.makeText(getApplicationContext(),"Please Enter Last Name", Toast.LENGTH_SHORT).show();
                        flag = false;
                    }

                    if( uNameText.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please Enter Username", Toast.LENGTH_SHORT).show();
                        flag = false;
                    }

                    if( passwdText.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please Enter Password",Toast.LENGTH_SHORT).show();
                        flag = false;
                    }

                    // Correct information, store in db
                   if (flag) {
                       Log.d("Flag true", "MADE IT");
                       Cursor mCursor;
                       String[] mProjection = new String[]{ AcctDB.COLUMN_ID };
                       //Cursor for the id column in content provider
                       String mSelection = AcctDB.COLUMN_ID + "= ?";
                       uNameText = uNameText.trim();
                       String[] mSelectionArgs = new String[]{uNameText};

                       Log.v("Try block", "cursor");
                       //Query
                       mCursor = getContentResolver().query(AcctDB.CONTENT_URI, mProjection, mSelection, mSelectionArgs, null);
                       if( mCursor == null )
                       {
                            Uri uri;

                           // No existing user
                           // Create new user for account
                           ContentValues cv = new ContentValues();
                           cv.put(AcctDB.COLUMN_ID, uNameText);
                           cv.put(AcctDB.COLUMN_FNAME, fNameText);
                           cv.put(AcctDB.COLUMN_LNAME, lNameText);
                           cv.put(AcctDB.COLUMN_ACCT_TYPE, acctType);
                           cv.put(AcctDB.COLUMN_PASSWD, passwdText);

                           uri = getContentResolver().insert(AcctDB.CONTENT_URI, cv);

                           Intent i = new Intent(InfoScreenFragment.this, AccountScreen.class);
                           Bundle b = new Bundle();
                           b.putString("username",uNameText);
                           i.putExtras(b);
                           startActivity(i);

                       } else {
                           Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_SHORT).show();
                       }

                   }

                }
        });

        // When back button clicked go back to register screen
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View click_view){
                Intent i = new Intent(InfoScreenFragment.this, ScreenRegister.class);
                startActivity(i);

            }
        });
    }
}
