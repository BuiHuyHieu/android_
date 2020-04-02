package com.example.trafficlight

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        imgLight.setOnClickListener(this)
        imgNeon.setOnClickListener(this)
        imgCar.setOnClickListener(this)
        imgTrafficLight.setOnClickListener(this)
        imgFlashLight.setOnClickListener(this)
    }



    override fun onClick(v: View?) {
        when(v?.id)
        {
            imgLight.id->{
                Handler().postDelayed(
                {
                    startActivity(Intent(applicationContext,light_on_off::class.java))

                },1000)
            }
            imgNeon.id->{
                Handler().postDelayed(
                    {
                        startActivity(Intent(applicationContext,neon_on_off::class.java))
                    },1000)
            }
            imgCar.id->
            {
                Handler().postDelayed(
                    {
                        startActivity(Intent(applicationContext, police_light::class.java))
                    }
                    ,1000)
            }
            imgTrafficLight.id->
            {
                Handler().postDelayed(
                    {
                        startActivity(Intent(applicationContext, traffic_light::class.java))
                    }
                    ,1000)
            }
            imgFlashLight.id->
            {
                Handler().postDelayed(
                    {
                        startActivity(Intent(applicationContext,flash_light::class.java))
                    }
                    ,1000)
            }

        }
    }
}