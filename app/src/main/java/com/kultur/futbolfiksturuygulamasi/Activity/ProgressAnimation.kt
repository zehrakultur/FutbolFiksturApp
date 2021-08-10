package com.kultur.futbolfiksturuygulamasi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.kultur.futbolfiksturuygulamasi.R

class ProgressAnimation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_animation)
    }

    override fun onResume() {
        super.onResume()

        object : CountDownTimer(4000,1000){
            override fun onFinish() {
                var intent= Intent(this@ProgressAnimation, Fikstur::class.java)
                startActivity(intent)
                finish()
            }
            override fun onTick(p0: Long) {

            }
        }.start()
    }


}