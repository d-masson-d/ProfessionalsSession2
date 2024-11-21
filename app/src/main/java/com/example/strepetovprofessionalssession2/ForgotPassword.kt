package com.example.strepetovprofessionalssession2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

class ForgotPassword : AppCompatActivity() {
    private lateinit var button_OTP: Button
    private lateinit var button_login_back: TextView
    private lateinit var editTextEmail: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        button_OTP = findViewById(R.id.Send_OTP)
        button_login_back = findViewById(R.id.button3_Signin)
        editTextEmail = findViewById(R.id.gmail_edit_forgot)

        button_OTP.isEnabled = false
        button_OTP.setTextColor(0xFFFFFFFF.toInt())
        editTextEmail.addTextChangedListener { validateInputs() }

        button_login_back.setOnClickListener() {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }
    }

    private fun validateInputs(){
        val email = editTextEmail.text.toString().trim()
        var isValid = true
        if (email.isEmpty() || !email.contains("@")) {
            editTextEmail.setBackgroundResource(R.drawable.background_edit_red)
            isValid = false
        } else {
            editTextEmail.setBackgroundResource(R.drawable.backgound_edit)
        }
        button_OTP.isEnabled = isValid
        button_OTP.setBackgroundColor(if (isValid) 0xFF0560FA.toInt() else 0xFFA7A7A7.toInt())
        button_OTP.setTextColor(0xFFFFFFFF.toInt())
        if (isValid) {
            button_OTP.setOnClickListener {
            val intent = Intent(this, OTPVerification::class.java)
                startActivity(intent)
            }
        }
    }
}

