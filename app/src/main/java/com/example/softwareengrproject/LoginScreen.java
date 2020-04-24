package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//File Description: Creates activity page for Login screen
//Design/interface of this activity - stored in: activity_loginscreen.xmlscreen.xml
public class LoginScreen extends AppCompatActivity {
    private String user_submit_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);

        Button login_submit = findViewById((R.id.login_submit));
        final EditText user_submit = findViewById(R.id.user);
        final EditText passwd_submit = findViewById(R.id.Password);

        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 user_submit_text = user_submit.getText().toString();
                String passwd_submit_text = passwd_submit.getText().toString();

                // Query database and check for existing user name
                Cursor mCursor;
                String[] mProjection = new String[]{ AcctDB.COLUMN_ACCT_ID, AcctDB.COLUMN_PASSWD };
                //Cursor for the id column in content provider
                String mSelection = AcctDB.COLUMN_ACCT_ID + "= ? AND " + AcctDB.COLUMN_PASSWD + "= ?";

                String[] mSelectionArgs = new String[]{user_submit_text, passwd_submit_text };
                Log.d("Login:",user_submit_text + passwd_submit_text);

                mCursor = getContentResolver().query(AcctDB.CONTENT_URI_ACCT, mProjection, mSelection, mSelectionArgs, null);
                if( mCursor.moveToFirst()  )
                    submit();
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect username or password!", Toast.LENGTH_SHORT ).show();
                }
                mCursor.close();
            }
        });

        Button Back = findViewById((R.id.BackButtonSettings));
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        
    }

    public void submit(){

        Intent intent = new Intent(this, AccountScreen.class);
        Bundle b = new Bundle();
        b.putString("username",user_submit_text);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void goBack(){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

}
