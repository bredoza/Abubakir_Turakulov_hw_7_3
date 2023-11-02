package com.example.abubakir_turakulov_hw_7_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val songName = intent.getStringExtra("SONG_NAME")

        val songNameTextView: TextView = findViewById(R.id.tv_select_song_name)
        songNameTextView.text = songName
    }
}