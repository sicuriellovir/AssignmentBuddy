package com.example.softwareengrproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.StringTokenizer;

public class messengeractivity extends AppCompatActivity {

    private EditText editText;
    String userName;
    String receiver;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messengeractivity);

        Bundle extras = getIntent().getExtras();
        back = findViewById(R.id.BackButtonMessenger);

        if ( extras != null )
        {
            userName = extras.getString("username");
            receiver = extras.getString("messageswith");
            Log.d("Bundle user", userName);
        }

        if( receiver.equals("")) {
            final EditText receiverInput = new EditText(messengeractivity.this);
            AlertDialog dialog = new AlertDialog.Builder(messengeractivity.this)
                                .setTitle("Add a Receiver")
                                .setMessage("Who do you want to send this to?")
                                .setView(receiverInput)
                                .setPositiveButton("Add Recipient", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        receiver = receiverInput.getText().toString();
                                        Log.d("Receiver", receiver);

                                        receiver_query(receiver);
                                    }
                                })
                                .create();
                        dialog.show();

        }

        else
        {
            Cursor mCursor;
            String []to_messages;
            String []from_messages;
            // Get messages from receiver
            String[] mProjection = new String[]{ AcctDB.COLUMN_MESSAGE };

            String mSelection = AcctDB.COLUMN_TO + "= ? AND " + AcctDB.COLUMN_FROM + " = ?";

            String[] mSelectionArgs = new String[]{userName, receiver};

            mCursor = getContentResolver().query(AcctDB.CONTENT_URI_MESSAGES, mProjection, mSelection, mSelectionArgs, null);

            if( mCursor != null ) {
                from_messages = new String[mCursor.getCount()];

                mCursor.moveToFirst();
                for(int i=0; i < from_messages.length; i++)
                {
                    from_messages[i] = mCursor.getString(0);
                }
            }

            // To receiver from user
            mProjection = new String[]{ AcctDB.COLUMN_MESSAGE };
            mSelection = AcctDB.COLUMN_TO + "= ? AND " + AcctDB.COLUMN_FROM + " = ?";
            mSelectionArgs = new String[]{receiver, userName};

            mCursor = getContentResolver().query(AcctDB.CONTENT_URI_MESSAGES, mProjection, mSelection, mSelectionArgs, null);

            if( mCursor != null ) {
                to_messages = new String[mCursor.getCount()];

                mCursor.moveToFirst();
                for(int i=0; i < to_messages.length; i++)
                {
                    to_messages[i] = mCursor.getString(0);
                }
            }
            else
            {
                // no from messages
            }

            //To display messages



        }

        editText = (EditText) findViewById(R.id.messengerEditText);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Go back to chat room
                Intent intent = new Intent(messengeractivity.this, messengerchats.class);
                Bundle b = new Bundle();
                b.putString("username", userName);
                intent.putExtras(b);

            }
        });

        Log.d("down","down here");
    }
    // On Click method
    public void sendMessage(View view) {
        String message = editText.getText().toString();

        // Send in the chat
        if (message.length() > 0) {
            Uri uri;
            Log.d("Click","clicked");
            ContentValues cv = new ContentValues();
            cv.put(AcctDB.COLUMN_TO, receiver);
            cv.put(AcctDB.COLUMN_FROM, userName);
            cv.put(AcctDB.COLUMN_MESSAGE, message);

            uri = getContentResolver().insert(AcctDB.CONTENT_URI_MESSAGES, cv);

            // Go back to chat room
            Intent intent = new Intent(messengeractivity.this, messengerchats.class);
            Bundle b = new Bundle();
            b.putString("username", userName);
            intent.putExtras(b);

            editText.getText().clear();
        }
    }

    public void receiver_query(String r) {
        Cursor mCursor;
        String[] mProjection = new String[]{AcctDB.COLUMN_ACCT_ID};

        StringTokenizer st = new StringTokenizer(receiver);

        if (st.countTokens() == 1) {
            String mSelection = AcctDB.COLUMN_ACCT_ID + " = ?";
            String[] mSelectionArgs = new String[]{receiver};
            mCursor = getContentResolver().query(AcctDB.CONTENT_URI_ACCT, mProjection, mSelection, mSelectionArgs, null);

            try {
                if (mCursor != null) {
                    // User types message and sends to user
                    Log.d("QUERY", "User exists!");

                } else {
                    // User does not exist


                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // First and last name submitted
//        else if (st.countTokens() == 2) {
//            Log.d("2 names","tokenized");
//            String mSelection = AcctDB.COLUMN_FNAME + " = ? AND " + AcctDB.COLUMN_LNAME + " = ?";
//            String[] names = new String[2];
//            int i = 0;
//            while (st.hasMoreTokens()) {
//                names[i] = st.nextToken();
//                i++;
//            }
//
//            String[] mSelectionArgs = new String[]{names[0], names[1]};
//            mCursor = getContentResolver().query(AcctDB.CONTENT_URI_ACCT, mProjection, mSelection, mSelectionArgs, null);
//            try {
//                if (mCursor != null) {
//                    // User types message and sends to user
//                    Log.d("Query",names[0] + names[1]);
//
//
//                } else {
//                    // User does not exist
//                    Log.d("Query", "No user");
//
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
   }
}
