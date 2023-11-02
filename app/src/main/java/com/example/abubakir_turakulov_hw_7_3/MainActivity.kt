package com.example.abubakir_turakulov_hw_7_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            val topFragment = MusicImageFragment()
            fragmentTransaction.replace(R.id.containerTop, topFragment)

            val bottomFragment = MusicListFragment()
            fragmentTransaction.replace(R.id.containerBottom, bottomFragment)

            fragmentTransaction.commit()
        }
    }
}