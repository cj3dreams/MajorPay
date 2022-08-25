package com.cj3dreams.majorpay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.cj3dreams.majorpay.view.ui.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(window){
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrgView, HomeFragment())
            .commit()
    }
}