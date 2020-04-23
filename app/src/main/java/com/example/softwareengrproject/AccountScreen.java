package com.example.softwareengrproject;

        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.view.View;
        import android.widget.Button;
        import android.os.Bundle;
        import android.util.Log;

public class AccountScreen extends AppCompatActivity {

    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_screen);

        Bundle extras = getIntent().getExtras();

        if ( extras != null )
        {
            userName = extras.getString("username");
            Log.d("Bundle user", userName);
        }
        Button buttonCourses = findViewById(R.id.button4);
        Button buttonTodo = findViewById(R.id.button3);
        Button buttonMessenger = findViewById(R.id.button5);
        Button buttonSettings = findViewById(R.id.button6);
        Button buttonCalendar = findViewById(R.id.button7);

        buttonCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityCourse();
            }
        });

        buttonTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityTodo();
            }
        });

        //TODO: Add button functionality for Messenger activity page
        //...buttonMessenger
        
       buttonSettings.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openActivitySettings();
           }
       });

        buttonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityCalendar();
            }
        });
    }


    public void openActivityCourse()
    {
        Intent intent = new Intent(this, CoursesActivity.class);
        Bundle b = new Bundle();
        b.putString("username",userName);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void openActivityTodo()
    {
         Intent intent = new Intent(this, ToDoList.class);
        Bundle b = new Bundle();
        b.putString("username",userName);
        intent.putExtras(b);
         startActivity(intent);
    }

    //TODO: Remove comment brackets once activity pages for Messenger has been implemented in master branch
/*
    public void openActivityMessenger()
    {
        Intent intent = new Intent(this, messenger.class);
        Bundle b = new Bundle();
        b.putString("username",userName);
        intent.putExtras(b);
        startActivity(intent);
    }

*/

    public void openActivitySettings()
    {
        Intent intent = new Intent(this, SettingsActivityPage.class);
        Bundle b = new Bundle();
        b.putString("username",userName);
        intent.putExtras(b);
        startActivity(intent);
    }


    public void openActivityCalendar()
    {
        Intent intent = new Intent(this, CalendarScreen.class);
        Bundle b = new Bundle();
        b.putString("username",userName);
        intent.putExtras(b);
        startActivity(intent);
    }
}
