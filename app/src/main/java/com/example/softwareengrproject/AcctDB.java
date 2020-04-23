package com.example.softwareengrproject;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* Database to store account information */
public class AcctDB extends ContentProvider {

    public static final String DBNAME = "AssignmentBuddyDB";
    //Account table constants
    public final static String TABLE_ACCTTABLE = "AccountTable";
    public final static String COLUMN_FNAME = "fName";
    public final static String COLUMN_LNAME = "lName";
    public final static String COLUMN_ACCT_ID = "acctuserID";
    public final static String COLUMN_ACCT_TYPE = "acctType";
    public final static String COLUMN_PASSWD = "userPasswd";
    private static final String SQL_CREATE_ACCT =
            "CREATE TABLE " + TABLE_ACCTTABLE + "(" + "_ID INTEGER PRIMARY KEY, " + COLUMN_ACCT_ID + " TEXT," + COLUMN_FNAME + " TEXT,"
                    + COLUMN_LNAME + " TEXT," + COLUMN_PASSWD + " TEXT," + COLUMN_ACCT_TYPE + " TEXT);";

    //Courses table constants
    public final static String TABLE_COURSESTABLE = "CoursesTable";
    public final static String COLUMN_COURSE_USER_ID = "courseuserID";
    public final static String COLUMN_COURSES = "courseList";
    private static final String SQL_CREATE_COURSES =
            "CREATE TABLE " + TABLE_COURSESTABLE + "(" + "_ID INTEGER PRIMARY KEY, " + COLUMN_COURSE_USER_ID + " TEXT," + COLUMN_COURSES + " TEXT);";

    //Grades table constants
    public final static String TABLE_GRADES_TABLE = "GradesTable";
    public final static String COLUMN_GRADES_USER_ID = "gradesuserID";
    public final static String COLUMN_COURSE_ID = "courseID";
    public final static String COLUMN_ASSIGNMENT_DUE_DAY = "dueDay";
    public final static String COLUMN_ASSIGNMENT_DUE_MONTH = "dueMonth";
    public final static String COLUMN_ASSIGNMENT = "assignmentName";
    public final static String COLUMN_GRADE = "assignmentGrade";
    private static final String SQL_CREATE_GRADES =
            "CREATE TABLE " + TABLE_GRADES_TABLE + "(" + "_ID INTEGER PRIMARY KEY, " + COLUMN_GRADES_USER_ID + " TEXT," + COLUMN_COURSE_ID + " TEXT," + COLUMN_ASSIGNMENT + " TEXT," + COLUMN_ASSIGNMENT_DUE_MONTH + " INTEGER,"+COLUMN_ASSIGNMENT_DUE_DAY + " INTEGER," +COLUMN_GRADE + " INTEGER);";


    // Messenger table constants
    public final static String TABLE_MESSENGER_TABLE = "MessagesTable";
    public final static String COLUMN_TO = "toID";
    public final static String COLUMN_MESSAGE = "messageText";
    public final static String COLUMN_FROM = "fromID";
    private static final String SQL_CREATE_MESSENGER =
            "CREATE TABLE " + TABLE_MESSENGER_TABLE + "(" + "_ID INTEGER PRIMARY KEY, " + COLUMN_TO + " TEXT," + COLUMN_MESSAGE +
                    " TEXT," + COLUMN_FROM + " TEXT);";

    public static final int DBVERSION = 4;
    public final static String PROVIDER = "com.example.softwareengrproject.provider";
    DataHelper mDataHelper;
    public final static Uri CONTENT_URI_GRADES = Uri.parse("content://com.example.softwareengrproject.provider" + "/" + TABLE_GRADES_TABLE);
    public final static Uri CONTENT_URI_COURSES = Uri.parse("content://com.example.softwareengrproject.provider" + "/" + TABLE_COURSESTABLE);
    public final static Uri CONTENT_URI_ACCT = Uri.parse("content://com.example.softwareengrproject.provider" + "/" + TABLE_ACCTTABLE);
    public final static Uri CONTENT_URI_MESSAGES = Uri.parse("content://com.example.softwareengrproject.provider" + "/" + TABLE_MESSENGER_TABLE);

    protected static final class DataHelper extends SQLiteOpenHelper {
        DataHelper(Context context) {
            super(context, DBNAME, null, DBVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ACCT);
            db.execSQL(SQL_CREATE_GRADES);
            db.execSQL(SQL_CREATE_MESSENGER);
            db.execSQL(SQL_CREATE_COURSES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

        }
    }

    private static final int ACCT = 1;
    private static final int ACCT_ID = 2;
    private static final int GRADES = 3;
    private static final int GRADES_ID = 4;
    private static final int COURSES = 5;
    private static final int COURSES_ID = 6;
    private static final int MESSENGER = 7;
    private static final int MESSENGER_ID = 8;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER, "/"+ TABLE_ACCTTABLE, ACCT);
        Log.d("uriMatcher:",PROVIDER + "/" + TABLE_ACCTTABLE);
        uriMatcher.addURI(PROVIDER, TABLE_ACCTTABLE + "/#", ACCT_ID);
        uriMatcher.addURI(PROVIDER, TABLE_GRADES_TABLE, GRADES);
        uriMatcher.addURI(PROVIDER, TABLE_GRADES_TABLE + "/#", GRADES_ID);
        uriMatcher.addURI(PROVIDER, "/"+TABLE_COURSESTABLE, COURSES);
        uriMatcher.addURI(PROVIDER, TABLE_COURSESTABLE + "/#", COURSES_ID);
        uriMatcher.addURI(PROVIDER, TABLE_MESSENGER_TABLE, MESSENGER);
        uriMatcher.addURI(PROVIDER, TABLE_MESSENGER_TABLE + "/#", MESSENGER_ID);
    }

    @Override
    public boolean onCreate() {
        mDataHelper = new DataHelper(getContext());
        return true;
    }
    /****** DELETE METHODS ******/
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case ACCT:
                return mDataHelper.getWritableDatabase().delete(TABLE_ACCTTABLE, selection, selectionArgs);
            case GRADES:
                return mDataHelper.getWritableDatabase().delete(TABLE_GRADES_TABLE, selection, selectionArgs);
            case COURSES:
                return mDataHelper.getWritableDatabase().delete(TABLE_COURSESTABLE, selection, selectionArgs);
            case MESSENGER:
                return mDataHelper.getWritableDatabase().delete(TABLE_MESSENGER_TABLE, selection, selectionArgs);

            default:
                return 0;
        }
    }

    /****** QUERY METHODS ******/
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        switch(uriMatcher.match(uri)) {
            case ACCT:
                return mDataHelper.getReadableDatabase().query(TABLE_ACCTTABLE, projection, selection, selectionArgs, null, null, sortOrder);
            case GRADES:
                return mDataHelper.getReadableDatabase().query(TABLE_GRADES_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
            case COURSES:
                Log.d("QUERY","inside");
                return mDataHelper.getReadableDatabase().query(TABLE_COURSESTABLE, projection, selection, selectionArgs, null, null, sortOrder);
            case MESSENGER:
                return mDataHelper.getReadableDatabase().query(TABLE_MESSENGER_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
            default:
                return null;
        }
    }
    @Override
    public String getType(Uri uri) {
        return null;
    }

    /****** UPDATE METHODS ******/
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch(uriMatcher.match(uri)){
            case ACCT:
                return mDataHelper.getWritableDatabase().update(TABLE_ACCTTABLE, values, selection, selectionArgs);
            case COURSES:
                return mDataHelper.getWritableDatabase().update(TABLE_COURSESTABLE, values, selection, selectionArgs);
            case GRADES:
                return mDataHelper.getWritableDatabase().update(TABLE_GRADES_TABLE, values, selection, selectionArgs);
            case MESSENGER:
                return mDataHelper.getWritableDatabase().update(TABLE_MESSENGER_TABLE, values, selection, selectionArgs);
            default:
                return 0;
        }
    }

    /****** INSERT METHODS ******/
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d("URI: ", uri.toString());
        switch(uriMatcher.match(uri)){
            case ACCT:
                String username = values.getAsString(COLUMN_ACCT_ID);
                String passwd = values.getAsString(COLUMN_PASSWD);
                String fname = values.getAsString(COLUMN_FNAME);
                String lname = values.getAsString(COLUMN_LNAME);
                String type = values.getAsString(COLUMN_ACCT_TYPE);

                if(username == null || passwd == null || fname == null || lname == null || type == null){
                    return null;
                }
                else if(username.equals("") || passwd.equals("")||fname.equals("")|| lname.equals("")||type.equals("")){
                    return null;
                }
                long id1 = mDataHelper.getWritableDatabase().insert(TABLE_ACCTTABLE, null, values);
                return Uri.withAppendedPath(CONTENT_URI_ACCT, "" + id1);
            case COURSES:
                Log.d("Insert", "courses");
                String courses_username = values.getAsString(COLUMN_COURSE_USER_ID);
                String courses_courseName = values.getAsString(COLUMN_COURSES);

                if(courses_username == null || courses_courseName == null){
                    return null;
                }
                else if(courses_username.equals("") || courses_courseName.equals("") ){
                    return null;
                }

                long id2 = mDataHelper.getWritableDatabase().insert(TABLE_COURSESTABLE, null, values);
                return Uri.withAppendedPath(CONTENT_URI_COURSES, "" + id2);
            case GRADES:
                String grades_userid = values.getAsString(COLUMN_GRADES_USER_ID);
                String course_id = values.getAsString(COLUMN_COURSE_ID);
                String assignment_name = values.getAsString(COLUMN_ASSIGNMENT);
                int grade = values.getAsInteger(COLUMN_GRADE);

                if(grades_userid == null || course_id == null  || assignment_name == null){
                    return null;
                }
                else if(grades_userid.equals("") || course_id.equals("") || assignment_name.equals("")|| grade < 0){
                    return null;
                }

                long id3 = mDataHelper.getWritableDatabase().insert(TABLE_GRADES_TABLE, null, values);
                return Uri.withAppendedPath(CONTENT_URI_GRADES, "" + id3);
            case MESSENGER:
                String to = values.getAsString(COLUMN_TO);
                String from = values.getAsString(COLUMN_FROM);
                String message = values.getAsString(COLUMN_MESSAGE);

                if(to == null || from == null  || message == null){
                    return null;
                }
                else if(to.equals("") || from.equals("") || message.equals("")){
                    return null;
                }

                long id4 = mDataHelper.getWritableDatabase().insert(TABLE_MESSENGER_TABLE, null, values);
                return Uri.withAppendedPath(CONTENT_URI_MESSAGES, "" + id4);
            default:
                int match = uriMatcher.match(uri);
                Log.d("different", Integer.toString(match));
                return null;
        }
    }

}