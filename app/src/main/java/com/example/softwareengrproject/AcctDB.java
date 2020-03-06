package com.example.softwareengrproject;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* Database to store account information */
public class AcctDB extends ContentProvider{
    public static final int DBVERSION = 1;
    public final static String DBNAME = "AccountDB";
    public final static String TABLE_NAMESTABLE = "AccountTable";
    public final static Uri CONTENT_URI = Uri.parse("content://com.example.softwareengrproject.provider");
    public final static String COLUMN_FNAME ="fName";
    public final static String COLUMN_LNAME ="lName";
    public final static String COLUMN_ID="userID";
    public final static String COLUMN_ACCT_TYPE = "acctType";
    public final static String COLUMN_PASSWD ="userPasswd";
    // String to create the database
    private static final String SQL_CREATE_MAIN =
            "CREATE TABLE " + TABLE_NAMESTABLE +"(" +"_ID INTEGER PRIMARY KEY, " + COLUMN_ID + " TEXT,"+ COLUMN_FNAME + " TEXT,"
                    + COLUMN_LNAME + " TEXT," + COLUMN_PASSWD + " TEXT," + COLUMN_ACCT_TYPE +" TEXT)";

    protected static final class AcctDBHelper extends SQLiteOpenHelper
    {
        AcctDBHelper( Context context )
        {
            super(context,"AssignmentBuddyDB", null, 1);

        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }
        @Override
        public void onUpgrade(SQLiteDatabase arg0,
                              int arg1, int arg2) {}
    }
    AcctDBHelper mOpenHelper;
    @Override
    public boolean onCreate() {
        mOpenHelper = new AcctDBHelper(getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String userid = values.getAsString(COLUMN_ID).trim();
        String fname = values.getAsString(COLUMN_FNAME).trim();
        String lname = values.getAsString(COLUMN_LNAME).trim();
        String acct_type = values.getAsString(COLUMN_ACCT_TYPE).trim();
        String passwd = values.getAsString(COLUMN_PASSWD).trim();

        //Check for invalid values
        if ( userid.equals("") )
            return null;
        if ( fname.equals("") )
            return null;
        if ( lname.equals("") )
            return null;
        if ( acct_type.equals("") )
            return null;
        if ( passwd.equals("") )
            return null;

        long id = mOpenHelper
                .getWritableDatabase()
                .insert(TABLE_NAMESTABLE, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        String userid = values.getAsString(COLUMN_ID).trim();
        String fname = values.getAsString(COLUMN_FNAME).trim();
        String lname = values.getAsString(COLUMN_LNAME).trim();
        String acct_type = values.getAsString(COLUMN_ACCT_TYPE).trim();
        String passwd = values.getAsString(COLUMN_PASSWD).trim();

        //Check for invalid values
        if ( userid.equals("") )
            return -1;
        if ( fname.equals("") )
            return -1;
        if ( lname.equals("") )
            return -1;
        if ( acct_type.equals("") )
            return -1;
        if ( passwd.equals("") )
            return -1;

        return mOpenHelper
                .getWritableDatabase()
                .update(TABLE_NAMESTABLE, values, selection, selectionArgs);
    }
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


}
