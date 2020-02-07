package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Collections;

public class screen_play extends AppCompatActivity {
    TableLayout tbLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_play);
        tbLayout = findViewById(R.id.tbLayout);
        int row=10;
        int column = 3;
        Collections.shuffle(screen_first.list);
        for(int i=1;i<=row;i++){
            TableRow tbRow = new TableRow(this);
            for(int j=1;j<=column;j++)
            {

                Resources r = getResources();
                float x = r.getDisplayMetrics().widthPixels/3;

                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams((int)x,(int)300);
                ImageView imgView = new ImageView(this);
                imgView.setLayoutParams(layoutParams);
                final int vitri = column*(i-1)+(j-1);
                int idHinh = getResources().getIdentifier(screen_first.list.get(vitri),"drawable",getPackageName());
                imgView.setImageResource(idHinh);
                tbRow.addView(imgView);

                imgView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(screen_play.this,screen_first.list.get(vitri),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();

                        String hinhChon = screen_first.list.get(vitri);
                        intent.putExtra("hinhchon",hinhChon);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
            }
            tbLayout.addView(tbRow);
        }
    }
}
