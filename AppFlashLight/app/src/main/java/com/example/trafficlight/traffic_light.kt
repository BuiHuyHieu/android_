package com.example.trafficlight

import android.graphics.drawable.LevelListDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_traffic_light.*

class traffic_light : AppCompatActivity() {
    var level_list = LevelListDrawable()
    val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traffic_light)
        supportActionBar?.hide()
        level_list = imgTraffic.drawable as LevelListDrawable
        handler.postDelayed(object:Runnable{
            override fun run() {
                var currentLevel = level_list.level
                if(currentLevel<3)
                {
                    level_list.level = currentLevel + 1;
                }
                else level_list.level = 0
                handler.postDelayed(this,300)
            }


        },500)

    }
}
