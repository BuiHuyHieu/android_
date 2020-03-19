package com.example.mfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends Activity {
    EditText edtUserName , edtPassword;
    Button btLogin , btRegister;
    TextView tvForgot;
    public static DatabaseLogin databaseLogin;
    String passWord;
    static String userName;

    public static DatabaseLogin DatabaseLogin() {
        return databaseLogin;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setID();
        setClickForgotPassword();
        databaseLogin = new DatabaseLogin(this);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = edtUserName.getText().toString().trim();
                passWord = edtPassword.getText().toString().trim();
                if (checkLogin(userName, passWord)) {
                    Toast.makeText(login.this, "Login Successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(login.this,welcome_screen.class));
                } else {
                    Toast.makeText(login.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,signup.class));
            }
        });

    }
    private void setID()
    {
        edtUserName = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btLogin = findViewById(R.id.btSignIn);
        btRegister = findViewById(R.id.btSignUp);
        tvForgot = findViewById(R.id.tvForgot);
    }
    private void setClickForgotPassword()
    {
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvForgot.setTextColor(Color.BLACK);
                    }
                }, 100);
                tvForgot.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.editt_color));
            }
        });
    }
    private boolean checkLogin(String userName, String passWord)
    {
        Cursor cursor = databaseLogin.readAllData();
        while(cursor.moveToNext()) {
            if (userName.equals(cursor.getString(1)) && passWord.equals(cursor.getString(2)))
                return true;
        }
        return false;
    }

    public static String getUserName() {
        return userName;
    }
}
