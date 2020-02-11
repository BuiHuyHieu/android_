package com.example.appmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RelativeLayout rl;
    ImageView img;
    Button bt;
    TextView tvGetAccount;
    EditText edUserName,edPassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl = findViewById(R.id.relley1);
        img = findViewById(R.id.imgeView_1);
        bt = findViewById(R.id.buttonLogin);
        tvGetAccount = findViewById(R.id.tvGetAccount);
        edUserName = findViewById(R.id.etUserName);
        edPassWord = findViewById(R.id.etPassWord);



        img.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rl.setVisibility(View.VISIBLE);

            }
        }, 3000);
        Login();
        getAccount();
    }

    private void Login()
    {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edUserName.getText().toString().compareTo("admin")==0&&edPassWord.getText().toString().compareTo("admin")==0)
                {
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,play_music.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.screen_start, R.anim.screen_exit);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Login Fail!" + edPassWord.getText().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void getAccount()
    {
        tvGetAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(100, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tvGetAccount.setTextColor(Color.GRAY);
                    }

                    @Override
                    public void onFinish() {
                        edUserName.setText(R.string.login_successful);
                        edPassWord.setText(R.string.login_successful);
                        tvGetAccount.setTextColor(Color.WHITE);
                    }
                }.start();



            }
        });
    }
}
