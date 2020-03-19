package com.example.mfood;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {
    Button btSubmit;
    EditText edtUsernameReg , edtConfirmPassword ,edtPassWord, edtEmail;
    String userName , passWord , cfPassword, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final DatabaseLogin databaseLogin = login.DatabaseLogin();
        Cursor cursor = databaseLogin.readAllData();
        Toast.makeText(this, String.valueOf(cursor.getCount()), Toast.LENGTH_LONG).show();
        SetID();

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                // not regex Email~
                if(!checkSignup(userName,cfPassword,passWord,email))
                {

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            databaseLogin.InsertData(userName, passWord);
                        }
                    },500);
                    startActivity(new Intent(signup.this,login.class));
                }
            }
        });
    }

    private void SetID()
    {
        btSubmit = findViewById(R.id.btSubmit);
        edtConfirmPassword = findViewById(R.id.edtConFirmPassword_Reg);
        edtUsernameReg = findViewById(R.id.edtUsername_Reg);
        edtPassWord = findViewById(R.id.edtPassword_Reg);
        edtEmail = findViewById(R.id.edtEmail);
    }

    private void getData()
    {
        userName = edtUsernameReg.getText().toString().trim();
        passWord = edtPassWord.getText().toString().trim();
        cfPassword = edtConfirmPassword.getText().toString().trim();
        email = edtEmail.getText().toString().trim();
    }
    private boolean checkSignup(String userName, String cfPassword , String passWord, String email)
    {
        if(userName.length()==0||passWord.length()==0||email.length()==0||cfPassword.length()==0|| cfPassword.compareTo(passWord)!=0) {
            Toast.makeText(getApplicationContext(), "Register Failed!", Toast.LENGTH_LONG).show();
            return false;
        }
        Cursor cursor = login.DatabaseLogin().readAllData();
        while(cursor.moveToNext())
        {

            if (userName.equals(cursor.getString(1)) && passWord.equals(cursor.getString(2))) {
                Toast.makeText(getApplicationContext(), "Account already exist", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

}
