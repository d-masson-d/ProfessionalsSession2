package com.example.strepetovprofessionalssession2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class OTPVerification : AppCompatActivity() {
    private lateinit var otpBox1: EditText
    private lateinit var otpBox2: EditText
    private lateinit var otpBox3: EditText
    private lateinit var otpBox4: EditText
    private lateinit var otpBox5: EditText
    private lateinit var otpBox6: EditText
    private lateinit var buttonSetNewPassword: Button
    private lateinit var timerTextView: TextView
    private var countDownTimer: CountDownTimer? = null
    private val totalTime: Long = 60000
    private var timeLeftInMillis: Long = totalTime
    private var isTimerRunning = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverification)
        otpBox1 = findViewById(R.id.otpBox1)
        otpBox2 = findViewById(R.id.otpBox2)
        otpBox3 = findViewById(R.id.otpBox3)
        otpBox4 = findViewById(R.id.otpBox4)
        otpBox5 = findViewById(R.id.otpBox5)
        otpBox6 = findViewById(R.id.otpBox6)
        buttonSetNewPassword = findViewById(R.id.buttonSetNewPassword)

        timerTextView = findViewById(R.id.timerTextView)

        startTimer()

        timerTextView.setOnClickListener {
            if (!isTimerRunning) {
                restartTimer()
            }
        }


        setOtpBoxWatcher(otpBox1, otpBox2)
        setOtpBoxWatcher(otpBox2, otpBox3)
        setOtpBoxWatcher(otpBox3, otpBox4)
        setOtpBoxWatcher(otpBox4, otpBox5)
        setOtpBoxWatcher(otpBox5, otpBox6)
        setOtpBoxWatcher(otpBox6)

        otpBox6.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkAllBoxesFilled()
            }


            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })



    }

    private fun startTimer() {
        isTimerRunning = true
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimer()
            }

            override fun onFinish() {
                timerTextView.text = " resend"
                timerTextView.setTextColor(0xFF0560FA.toInt())
                isTimerRunning = false
            }
        }.start()
    }

    private fun updateTimer() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60

        val timeLeftFormatted = String.format(" resend after %d:%02d", minutes, seconds)
        timerTextView.text = timeLeftFormatted
        timerTextView.setTextColor(0xFFA7A7A7.toInt())
    }

    private fun restartTimer() {
        timeLeftInMillis = totalTime
        timerTextView.setTextColor(0xFFA7A7A7.toInt())
        startTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }

    private fun setOtpBoxWatcher(current: EditText, next: EditText? = null) {
        current.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    current.setBackgroundResource(R.drawable.otp_edittext_background)
                } else {
                    current.setBackgroundResource(R.drawable.otp_edittext_background2)
                    next?.requestFocus()
                }
                checkAllBoxesFilled()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun checkAllBoxesFilled() {
        val isAllFilled = otpBox1.text.isNotEmpty() && otpBox2.text.isNotEmpty() &&
                otpBox3.text.isNotEmpty() && otpBox4.text.isNotEmpty() &&
                otpBox5.text.isNotEmpty() && otpBox6.text.isNotEmpty()

        buttonSetNewPassword.isEnabled = isAllFilled
        buttonSetNewPassword.setBackgroundColor(if (isAllFilled) 0xFF0560FA.toInt() else 0xFFA7A7A7.toInt())
        buttonSetNewPassword.setOnClickListener(){
            val intent = Intent(this, NewPassword::class.java)
            startActivity(intent)
        }
    }

}