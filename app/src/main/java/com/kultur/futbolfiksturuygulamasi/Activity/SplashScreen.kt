package com.kultur.futbolfiksturuygulamasi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.kultur.futbolfiksturuygulamasi.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onResume() {
        super.onResume()

        object : CountDownTimer(4000,1000){
            override fun onFinish() {
                var intent= Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
            }
            override fun onTick(p0: Long) {

            }
        }.start()
    }
}