package com.example.strepetovprofessionalssession2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

class LogIn : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogIn: Button
    private lateinit var textViewSignUp: TextView
    private lateinit var textViewForgotPassword: TextView
    private var isConfirmPasswordVisible = false
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        editTextEmail = findViewById(R.id.editTextEmail_login)
        editTextPassword = findViewById(R.id.editTextTextPassword_login)
        buttonLogIn = findViewById(R.id.Log_in)
        textViewSignUp = findViewById(R.id.button2_sinUp)
        textViewForgotPassword = findViewById(R.id.forgot_password)
        buttonLogIn.setTextColor(0xFFFFFFFF.toInt())
        setupPasswordToggle(editTextPassword)
        editTextEmail.addTextChangedListener { validateInputs() }
        editTextPassword.addTextChangedListener { validateInputs() }
        textViewSignUp.setOnClickListener {
            val intent = Intent(this, SingUp::class.java)
            startActivity(intent)
        }

        textViewForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
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
        if (editText == editTextPassword) {
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
    private fun validateInputs(){
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString()

        var isValid = true


        if (email.isEmpty() || !email.contains("@")) {
            editTextEmail.setBackgroundResource(R.drawable.background_edit_red)
            isValid = false
        } else {
            editTextEmail.setBackgroundResource(R.drawable.backgound_edit)
        }


        if (password.isEmpty()) {
            editTextPassword.setBackgroundResource(R.drawable.background_edit_red)
            editTextPassword.setTextColor(0xFFED3A3A.toInt())
            isValid = false
        } else {
            editTextPassword.setBackgroundResource(R.drawable.backgound_edit)
            editTextPassword.setTextColor(0xFF3A3A3A.toInt())
        }

        buttonLogIn.isEnabled = isValid
        buttonLogIn.setBackgroundColor(if (isValid) 0xFF0560FA.toInt() else 0xFFA7A7A7.toInt())
        buttonLogIn.setTextColor(0xFFFFFFFF.toInt())
        if (isValid) {
            buttonLogIn.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
