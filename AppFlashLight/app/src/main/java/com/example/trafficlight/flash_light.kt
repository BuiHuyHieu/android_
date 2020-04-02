package com.example.trafficlight

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_flash_light.*
import java.util.jar.Manifest

class flash_light : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_light)
        supportActionBar?.hide()

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),1)
        val hasCamera = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        btFlashLight.setOnCheckedChangeListener { buttonView, isChecked ->
            if(hasCamera)
            {
                if(isChecked)
                {
                    turnOn()
                }
                else turnOff()
            }
            else Toast.makeText(applicationContext,"Your device not available for Flash Light!",Toast.LENGTH_LONG).show()
        }

    }

    private fun turnOn() {
        val camera = getSystemService(Context.CAMERA_SERVICE)
        val cameraManager:CameraManager = camera as CameraManager
        val cameraID = cameraManager.cameraIdList[0]
        cameraManager.setTorchMode(cameraID,true)
    }
    private fun turnOff() {
        val camera = getSystemService(Context.CAMERA_SERVICE)
        val cameraManager:CameraManager = camera as CameraManager
        val cameraID = cameraManager.cameraIdList[0]
        cameraManager.setTorchMode(cameraID,false)
    }
}
