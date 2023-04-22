package com.example.android_todo_project.ui;

import static com.example.android_todo_project.utils.constants.LoginPreference;
import static com.example.android_todo_project.utils.constants.pinKey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.android_todo_project.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedPref = getSharedPreferences(LoginPreference, Context.MODE_PRIVATE);

        //Hide toolbar
        getSupportActionBar().hide();

        //Check if user is already logged In or not
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String getPin= sharedPref.getString(pinKey,"");
                if(getPin.equals("")){
                    //Login
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                }else{
                    //Home
                    Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(i);
                }
            }
        }, 2000);



    }
}