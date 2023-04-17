package com.example.android_todo_project.ui;

import static com.example.android_todo_project.utils.constants.LoginPreference;
import static com.example.android_todo_project.utils.constants.pinKey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.android_todo_project.R;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    EditText etPin;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPin = findViewById(R.id.etPin);
        btnLogin = findViewById(R.id.btnLogin);
        sharedpreferences = getSharedPreferences(LoginPreference, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getPin = etPin.getText().toString();
                editor.putString(pinKey, getPin);
                editor.commit();
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);

            }
        });
    }
}