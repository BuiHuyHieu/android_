package com.example.trafficlight

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_light_on_off.*


class light_on_off : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light_on_off)
        buttonLight.setOnClickListener{
            val mediaPlayer = MediaPlayer.create(this,R.raw.sound_switch)
            mediaPlayer.start()
        }
    }
}
