package com.example.appmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

public class play_music extends AppCompatActivity {
    TextView tvNameSong,tvTimeStart,tvTimeEnd;
    SeekBar sb;
    ImageView imgRotate,imgPlay,imgNextRight,imgPrevious;
    List<Song> listSong;
    MediaPlayer mediaPlayer;
    int positionCurrent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        setID();
        imgRotate.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_music_img));
        AddSong();
        playSong(0);
        actionForImgPlay();
    }


    private void UpdateTimeSong()
    {
        final Handler handler;
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvTimeStart.setText(formatTime(mediaPlayer.getCurrentPosition()));
                sb.setProgress(mediaPlayer.getCurrentPosition());
                if(sb.getProgress()==sb.getMax())
                {
                    Toast.makeText(play_music.this, listSong.get(++positionCurrent).getNameSong(), Toast.LENGTH_LONG).show();
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    playSong(positionCurrent);
                    imgPlay.setImageResource(R.drawable.pause);
                    sb.setProgress(0);
                    actionForImgPlay();
                }
                handler.postDelayed(this, 1000);
            }
        }, 0);


    }
    private void setID()
    {
        tvNameSong = findViewById(R.id.tvNameSong);
        imgRotate = findViewById(R.id.imgMusicRotate);
        tvTimeStart = findViewById(R.id.timeSongStart);
        tvTimeEnd = findViewById(R.id.timeSongEnd);
        imgPlay = findViewById(R.id.imgPlay);
        imgNextRight = findViewById(R.id.imgNext);
        imgPrevious = findViewById(R.id.imgPrevious);
        sb = findViewById(R.id.seekBar);
    }

    private void AddSong()
    {
        listSong = new ArrayList<Song>();
        listSong.add(new Song("Anh Thanh Niên", R.raw.anh_thanh_nien));
        listSong.add(new Song("Có Em Đời Bỗng Vui", R.raw.co_em_doi_bong_vui));
        listSong.add(new Song("Hết Thương Cạn Nhớ", R.raw.het_thuong_can_nho));
        listSong.add(new Song("Lá Xa Lìa Cành", R.raw.la_xa_lia_canh));
        listSong.add(new Song("Nắm", R.raw.nam_minh_vuong));
        listSong.add(new Song("OK", R.raw.ok_binz));
        listSong.add(new Song("Tặng Anh Cho Cô Ấy", R.raw.tang_anh_cho_co_ay));
        listSong.add(new Song("Thuận Theo Ý Trời", R.raw.thuan_theo_y_troi));
    }

    private void actionForImgPlay()
    {
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying())
                {
                    imgPlay.setImageResource(R.drawable.play_button);
                    mediaPlayer.pause();
                    //mediaPlayer.release();
                }
                else
                {
                    imgPlay.setImageResource(R.drawable.pause);
                    mediaPlayer.start();
                }
            }
        });
        UpdateTimeSong();
        UpdateSeekBar();
        actionImgArrow();

    }


    private void playSong(int positon)
    {
        mediaPlayer = MediaPlayer.create(this, listSong.get(positon).getMp3());
        tvNameSong.setText(listSong.get(positon).getNameSong());
        mediaPlayer.start();

        tvTimeEnd.setText(formatTime(mediaPlayer.getDuration()));
        sb.setMax(mediaPlayer.getDuration());
        positionCurrent=positon;
    }

    private String formatTime(int time)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

        return simpleDateFormat.format(time);
    }

    private void UpdateSeekBar()
    {
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvTimeStart.setText(formatTime(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tvTimeStart.setText(formatTime(seekBar.getProgress()));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvTimeStart.setText(formatTime(seekBar.getProgress()));
                mediaPlayer.seekTo(seekBar.getProgress());

            }
        });
    }

    private void actionImgArrow()
    {
        imgNextRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(200, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        imgNextRight.setImageResource(R.drawable.next_right);
                    }

                    @Override
                    public void onFinish() {
                        imgNextRight.setImageResource(R.drawable.next);
                    }
                }.start();
                Toast.makeText(play_music.this, listSong.get(++positionCurrent).getNameSong(), Toast.LENGTH_LONG).show();
                mediaPlayer.stop();
                mediaPlayer.release();
                playSong(positionCurrent);
                imgPlay.setImageResource(R.drawable.pause);
                sb.setProgress(0);
                actionForImgPlay();
            }
        });

        imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(200, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        imgPrevious.setImageResource(R.drawable.next_left_click);
                    }

                    @Override
                    public void onFinish() {
                        imgPrevious.setImageResource(R.drawable.next_left);
                    }
                }.start();
                mediaPlayer.stop();
                mediaPlayer.release();
                imgPlay.setImageResource(R.drawable.pause);
                sb.setProgress(0);
                actionForImgPlay();
                if(positionCurrent>0)
                {
                    playSong(--positionCurrent);
                    Toast.makeText(play_music.this, listSong.get(positionCurrent).getNameSong(), Toast.LENGTH_LONG).show();
                }
                 else playSong(positionCurrent);

            }
        });
    }
}
