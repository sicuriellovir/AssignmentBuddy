package com.example.softwareengrproject;

        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.view.View;
        import android.widget.Button;
        import android.os.Bundle;
        import android.util.Log;

public class AccountScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_screen);

        Button buttonCourses = findViewById(R.id.button4);
        Button buttonTodo = findViewById(R.id.button3);
        Button buttonMessenger = findViewById(R.id.button5);
        Button buttonSettings = findViewById(R.id.button6);

        buttonCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityCourse();
            }
        });
    }


    public void openActivityCourse()
    {
        Intent intent = new Intent(this, CoursesActivity.class);
        startActivity(intent);
    }
    /*
    public void openActivityTodo()
    {
         Intent intent = new Intent(this, todopage.class);
         startActivity(intent);
    }

    public void openActivityMessenger()
    {
        Intent intent = new Intent(this, messenger.class);
        startActivity(intent);
    }

    public void openActivitySettings()
    {
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);
    }
*/
}
