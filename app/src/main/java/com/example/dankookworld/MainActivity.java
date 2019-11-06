package com.example.dankookworld;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button loginyes, loginno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        loginyes = findViewById(R.id.loginyes);
        loginno = findViewById(R.id.loginno);

        loginyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yesintent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(yesintent);
            }
        });

        loginno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nointent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(nointent);
            }
        });
    }
}
