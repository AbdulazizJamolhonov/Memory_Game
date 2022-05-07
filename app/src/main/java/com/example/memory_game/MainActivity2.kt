package com.example.memory_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    private var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        start.setOnClickListener {
            if (index != 0) {
                MyData.index = index
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        easy.setOnCheckedChangeListener { _, b ->
            if (b) {
                index = 1
            }
        }
        medium.setOnCheckedChangeListener { _, b ->
            if (b) {
                index = 2
            }
        }
        hard.setOnCheckedChangeListener { _, b ->
            if (b) {
                index = 3
            }
        }
    }
}