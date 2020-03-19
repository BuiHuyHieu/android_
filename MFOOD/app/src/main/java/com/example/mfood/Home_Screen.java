package com.example.mfood;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Home_Screen extends AppCompatActivity implements connect {
    fragment_listView fragment_listView;
    LinearLayout fragment_listFood , fragment_home;
    Information_Food information_food=null;
    ImageView imgAvatar;
    int i=0,temp;
    connect connect;
    int request_code=111;
    boolean check;
    TextView tvProFile , tvName, tvAddFood,tvSignout;
    public static List<Information_Food> listFood  = new ArrayList<Information_Food>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__screen);
        fragment_listFood = findViewById(R.id.fragment_listFood);
        imgAvatar = findViewById(R.id.avatar);
        fragment_home = findViewById(R.id.fragment_Home);
        tvProFile = findViewById(R.id.tvProfile);
        tvName = findViewById(R.id.tvName);
        tvAddFood = findViewById(R.id.tvAddFood);
        tvSignout = findViewById(R.id.tvSignOut);
        openAddFood();
        FragmentManager fragment = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragment.beginTransaction();
        fragment_listView = new fragment_listView();
        fragmentTransaction.add(R.id.fragment_listFood, fragment_listView,"fragment_list");
        fragmentTransaction.commit();
        openProfile();
        updateProFile();
        clickSignout();
            }

    @Override
    public void CHECK_CLICK(boolean check) {
        Log.d("compile", String.valueOf(check));
        int width = getWindowManager().getDefaultDisplay().getWidth();
        if(!check)
        {
            fragment_listFood.setTranslationX(-width/2);
            fragment_listFood.setAlpha((float) 1);
        }
        else
        {
            fragment_listFood.setTranslationX(0);
            fragment_home.setTranslationX(0);
        }
    }


    private void openProfile()
    {
        tvProFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(500,100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tvProFile.setTextColor(Color.BLACK);
                    }

                    @Override
                    public void onFinish() {
                        tvProFile.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_color));
                    startActivityForResult(new Intent(Home_Screen.this,profile.class),request_code);
                    }
                }.start();
            }
        });
    }
    private void updateProFile()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(login.getUserName(), MODE_PRIVATE);
        tvName.setText(sharedPreferences.getString("NAME_PROFILE", "ADMIN"));
    }

    private void openAddFood()
    {
        tvAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(500,100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tvAddFood.setTextColor(Color.BLACK);
                    }

                    @Override
                    public void onFinish() {
                        tvAddFood.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_color));
                        startActivityForResult(new Intent(Home_Screen.this,addfood.class),request_code);
                    }
                }.start();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==request_code && resultCode==RESULT_OK)
        {
            Information_Food information_food = (Information_Food) data.getBundleExtra("data").getSerializable("food");
            listFood.add(information_food);
        }
    }

    private void clickSignout()
    {
        tvSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(500,100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tvSignout.setTextColor(Color.BLACK);
                    }

                    @Override
                    public void onFinish() {
                        tvSignout.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_color));
                        startActivity(new Intent(Home_Screen.this,login.class));
                    }
                }.start();
            }
        });
    }

}

