package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonPlay,buttonHelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setID();
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showDialogHelp();
            }
        });
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,screen_first.class);
                startActivity(intent);
            }
        });
    }


    private void setID()
    {
        buttonPlay = findViewById(R.id.button_play);
        buttonHelp = findViewById(R.id.button_help);
    }
    private void showDialogHelp()
    {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Help");
        dialog.setContentView(R.layout.guideplayer);
        dialog.show();
    }

}
