package com.example.trafficlight

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.imgLight
import kotlinx.android.synthetic.main.activity_police_light.*

class police_light : AppCompatActivity() {
    var check = false
    val handler = Handler()
    var mediaPlayer = MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_police_light)
        supportActionBar?.hide()
        mediaPlayer = MediaPlayer.create(this,R.raw.music_police)
        mediaPlayer.start()
        handler.postDelayed(object : Runnable
            {
                override fun run() {
                    check=!check
                    imgLightPolice.isChecked = check
                    handler.postDelayed(this,200)
                }
            }
            ,200)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
        police_light().finish()
        Log.d("A","onDestroy")
    }

}
