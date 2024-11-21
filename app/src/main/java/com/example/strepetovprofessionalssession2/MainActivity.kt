package com.example.strepetovprofessionalssession2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button:TextView = findViewById(R.id.click_st)


        button.setOnClickListener {
            val intent = Intent(this, SingUp::class.java)
            startActivity(intent)
        }
    }
}