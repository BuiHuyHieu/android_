package com.example.mastermind;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class screen_first extends AppCompatActivity {
    public static List<String> list;
    TextView timeStart;
    ImageView imgFirst;
    LinearLayout layout;
    int REQUEST_CODE = 111;
    int time = 3;
    int idHinh;
    int sumManChoi = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_first);
        setID();
        timeStart.setText(time + "");
        CountDownTimer timer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeStart.setText(time + "");
                time--;
                if (time <= 2) {
                    timeStart.setTextColor(Color.YELLOW);
                }
                if (time <= 1) {
                    timeStart.setTextColor(Color.RED);
                }
            }

            @Override
            public void onFinish() {
                final Snackbar snackbar;
                snackbar = Snackbar.make(layout, "Time out!", Snackbar.LENGTH_LONG).setAction("!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                snackbar.setActionTextColor(Color.RED);
                snackbar.show();
                imgFirst.setImageResource(R.drawable.ic_launcher_background);

                Intent intent = new Intent(screen_first.this, screen_play.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        };
        timer.start();
        addImageToList();

        idHinh = getResources().getIdentifier(list.get(0), "drawable", getPackageName());
        imgFirst.setImageResource(idHinh);
    }

    private void setID() {
        timeStart = findViewById(R.id.tvTimeStart);
        imgFirst = findViewById(R.id.imgFirst);
        layout = findViewById(R.id.lnLayout);
    }


    private void addImageToList() {
        list = new ArrayList<String>();
        for (int i = 1; i <= 30; i++) {
            String name = "bird_" + i;
            list.add(name);
        }
        Collections.shuffle(list);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            final ImageView imgResult = findViewById(R.id.imgResult);

            String temp = data.getStringExtra("hinhchon");
            int id = getResources().getIdentifier(temp, "drawable", getPackageName());

            imgFirst.setImageResource(idHinh);
            imgResult.setImageResource(id);

            if (id == idHinh) {
                timeStart.setTextColor(Color.GREEN);
                timeStart.setText("Correct");
                sumManChoi++;
            } else {
                timeStart.setTextColor(Color.RED);
                timeStart.setText("Not correct");
            }
            new CountDownTimer(2000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(screen_first.this);
                    dialog.setTitle("Question");
                    dialog.setMessage("Bạn đã vượt qua " + sumManChoi +" màn chơi" +"\n" + "Bạn có muốn chơi nữa không?");
                    dialog.setIcon(R.drawable.image_questions);
                    dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new CountDownTimer(2000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {

                                }

                                @Override
                                public void onFinish() {
                                    time = 3;
                                    timeStart.setText(time + "");
                                    imgResult.setImageResource(0);
                                    CountDownTimer timer = new CountDownTimer(4000, 1000) {
                                        @Override
                                        public void onTick(long millisUntilFinished) {
                                            timeStart.setText(time + "");
                                            time--;
                                            if (time <= 3) {
                                                timeStart.setTextColor(Color.YELLOW);
                                            }
                                            if (time <= 1) {
                                                timeStart.setTextColor(Color.RED);
                                            }
                                        }

                                        @Override
                                        public void onFinish() {
                                            final Snackbar snackbar;
                                            snackbar = Snackbar.make(layout, "Time out!", Snackbar.LENGTH_LONG).setAction("!", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                }
                                            });
                                            snackbar.setActionTextColor(Color.RED);
                                            snackbar.show();
                                            imgFirst.setImageResource(R.drawable.ic_launcher_background);

                                            Intent intent = new Intent(screen_first.this, screen_play.class);
                                            startActivityForResult(intent, REQUEST_CODE);
                                        }
                                    };
                                    timer.start();
                                    addImageToList();

                                    idHinh = getResources().getIdentifier(list.get(0), "drawable", getPackageName());
                                    imgFirst.setImageResource(idHinh);
                                }
                            }.start();
                            dialog.dismiss();
                        }
                    });

                    dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            startActivity(new Intent(screen_first.this, MainActivity.class));
                        }
                    });
                    AlertDialog dialogShow = dialog.create();
                    dialogShow.show();
                }

            }.start();
        }
    }
}
