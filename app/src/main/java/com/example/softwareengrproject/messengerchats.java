package com.example.softwareengrproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class messengerchats extends AppCompatActivity implements  AdapterView.OnItemClickListener{

    String userName;
    String [] chats;
    ListView lv;
    Button backButton, sendNewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messengerchats);

        lv = (ListView) findViewById(R.id.chats_listview);
        backButton = (Button) findViewById(R.id.msgrchatback);
        sendNewButton = (Button) findViewById(R.id.addChatButton);

        Bundle extras = getIntent().getExtras();

        if ( extras != null ) {
            userName = extras.getString("username");
            Log.d("Bundle user", userName);
        }

        // Query Messenger Table to display existing chats with other users
        Cursor mCursor;

        String[] mProjection = new String[]{ AcctDB.COLUMN_TO, AcctDB.COLUMN_FROM };

        String mSelection = AcctDB.COLUMN_TO + "= ? OR " + AcctDB.COLUMN_FROM + " = ?";

        String[] mSelectionArgs = new String[]{userName, userName};

        mCursor = getContentResolver().query(AcctDB.CONTENT_URI_MESSAGES, mProjection, mSelection, mSelectionArgs, null);

        try {
            if (mCursor != null) {
                Log.d("OnCreate", "Cursor not null");
                mCursor.moveToFirst();

                String[] temp = new String[mCursor.getCount()];
                int entries = 0;

                for (int i = 0; i < temp.length; i++) {
                    if (userName.equals(mCursor.getString(0)))
                        temp[i] = mCursor.getString(1);

                    else if (userName.equals(mCursor.getString(1)))
                        temp[i] = mCursor.getString(0);

                    for (int j = 0; j < temp.length; j++) {
                        for (int k = j + 1; k < temp.length; k++) {
                            if (temp[j].equals(temp[k])) {
                                //do nothing
                            } else {
                                entries++;
                            }
                        }
                    }
                }


                Log.d("Num entries", Integer.toString(entries));
                if( entries > 0 )
                {
                chats = new String[entries];
                int k = 0;
                for (int i = 0; i < temp.length; i++)
                {
                    int exists = 0;
                    for (int j = 0; j < k; j++) {
                        if (temp[i].equals(chats[j])) {
                            exists = 1;
                            break;
                        }
                    }
                    if (exists == 0) {
                        chats[k] = temp[i];
                        k++;
                    }

                } }
                mCursor.moveToNext();
            }

            else
            {
                Log.d("Chats ", "No chats");
                Toast.makeText(getApplicationContext(), "No chats to display! Send a message.",Toast.LENGTH_LONG).show();
            }
            if( chats != null ) {
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, chats);
                lv.setAdapter(arrayAdapter);
                lv.setVisibility(View.VISIBLE);
                lv.setOnItemClickListener(this);
                mCursor.close();
            }


        }catch ( SQLException e){
            e.printStackTrace();}

        sendNewButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( messengerchats.this, messengeractivity.class);
                intent.putExtra("username", userName);

                // Check value in messengeractivity
                intent.putExtra("messageswith", "");
                startActivity(intent);
            }

        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( messengerchats.this, AccountScreen.class);
                intent.putExtra("username", userName);
                startActivity(intent);
            }
        });

    }

    public void onItemClick(AdapterView<?> parent, View view, int i, long l )
    {
        Log.d("OnItemClick","Made it");
        Intent intent = new Intent(messengerchats.this, messengeractivity.class);
        intent.putExtra("messageswith", chats[i]);
        intent.putExtra("username", userName);
        startActivity(intent);


    }
}
