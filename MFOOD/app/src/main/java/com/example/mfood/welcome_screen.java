package com.example.mfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

public class welcome_screen extends AppCompatActivity {
    TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        tvName = findViewById(R.id.tvGuys);
        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(welcome_screen.this, "Welcome to MFOOD", Toast.LENGTH_LONG).show();
                tvName.setText(login.getUserName());
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(welcome_screen.this,Home_Screen.class));
            }
        }.start();

    }
}
