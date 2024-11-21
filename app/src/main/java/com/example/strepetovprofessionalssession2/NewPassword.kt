package com.example.strepetovprofessionalssession2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText

class NewPassword : AppCompatActivity() {
    private lateinit var TextPassword: EditText
    private lateinit var ConfrimPassword: EditText
    private lateinit var buttonLoin: Button
    private var isConfirmPasswordVisible = false
    private var isPasswordVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)

        TextPassword = findViewById(R.id.password_text)
        ConfrimPassword = findViewById(R.id.confirmPassword)
        buttonLoin = findViewById(R.id.Log_in)

        setupPasswordToggle(TextPassword)
        setupPasswordToggle(ConfrimPassword)

        ConfrimPassword.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                checkPasswordsMatch()
            }
        }
    }

    private fun setupPasswordToggle(editText: EditText) {
        editText.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = editText.compoundDrawablesRelative[2]
                if (drawableEnd != null && event.rawX >= (editText.right - drawableEnd.bounds.width())) {
                    togglePasswordVisibility(editText)
                    return@setOnTouchListener true
                }
            }
            false
        }
    }

    private fun togglePasswordVisibility(editText: EditText) {
        if (editText == TextPassword) {
            isPasswordVisible = !isPasswordVisible
        } else {
            isConfirmPasswordVisible = !isConfirmPasswordVisible
        }

        if (isPasswordVisible) {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            editText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eye, 0)
        } else {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            editText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eye_slash, 0)
        }

        editText.setSelection(editText.text.length)
    }

    private fun checkPasswordsMatch() {
        val password = TextPassword.text.toString()
        val confirmPassword = ConfrimPassword.text.toString()

        if (password == confirmPassword) {
            ConfrimPassword.setBackgroundResource(R.drawable.backgound_edit)
            ConfrimPassword.setTextColor(0xFF3A3A3A.toInt())
            buttonLoin.isEnabled = true
            buttonLoin.setOnClickListener(){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            buttonLoin.setBackgroundColor(0xFF0560FA.toInt())
        } else {
            ConfrimPassword.setBackgroundResource(R.drawable.background_edit_red)
            ConfrimPassword.setTextColor(0xFFED3A3A.toInt())
            buttonLoin.isEnabled = false
            buttonLoin.setBackgroundColor(0xFFA7A7A7.toInt())
        }
    }
}
