package com.example.strepetovprofessionalssession2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.Uri
import android.text.InputType
import android.view.MotionEvent

import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener


class SingUp : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextNumber: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var TextPassword: EditText
    private lateinit var ConfrimPassword: EditText
    private lateinit var button_singUp: Button
    private lateinit var button_singIn: TextView
    private lateinit var textViewWeb1: TextView
    private lateinit var textViewWeb2: TextView
    private lateinit var checkBox1: CheckBox
    private var isConfirmPasswordVisible = false
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        textViewWeb1 = findViewById(R.id.text_web_1)
        textViewWeb2 = findViewById(R.id.text_web_2)
        checkBox1 = findViewById(R.id.myCheckBox)
        button_singIn = findViewById(R.id.button_sinIn)
        button_singUp = findViewById(R.id.button_sinUp)

        editTextName = findViewById(R.id.name_Field)
        editTextNumber = findViewById(R.id.phoneNumber_Field)
        editTextEmail = findViewById(R.id.email_Field)
        TextPassword = findViewById(R.id.password_text)
        ConfrimPassword = findViewById(R.id.confirmPassword)

        button_singUp.isEnabled = false
        button_singUp.setBackgroundColor(0xFFA7A7A7.toInt())
        button_singUp.setTextColor(0xFFFFFFFF.toInt())

        setupPasswordToggle(TextPassword)
        setupPasswordToggle(ConfrimPassword)

        ConfrimPassword.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                checkPasswordsMatch()
                validateInputs()
            }
        }
        TextPassword.addTextChangedListener { validateInputs() }
        ConfrimPassword.addTextChangedListener { validateInputs() }
        editTextName.addTextChangedListener { validateInputs() }
        editTextNumber.addTextChangedListener { validateInputs() }
        editTextEmail.addTextChangedListener { validateInputs() }

        val link_agreement = "https://your-link.com"
        textViewWeb1.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link_agreement))
            startActivity(intent)
        }
        textViewWeb2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link_agreement))
            startActivity(intent)
        }

        button_singIn.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
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
        } else {
            ConfrimPassword.setBackgroundResource(R.drawable.background_edit_red)
            ConfrimPassword.setTextColor(0xFFED3A3A.toInt())
        }
        validateInputs()
    }

    private fun validateInputs() {
        val name = editTextName.text.toString().trim()
        val number = editTextNumber.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val password = TextPassword.text.toString()
        val confirmPassword = ConfrimPassword.text.toString()

        var isValid = true

        if (name.isEmpty()) {
            editTextName.setBackgroundResource(R.drawable.background_edit_red)
            isValid = false
        } else {
            editTextName.setBackgroundResource(R.drawable.backgound_edit)
        }

        if (number.isEmpty()) {
            editTextNumber.setBackgroundResource(R.drawable.background_edit_red)
            isValid = false
        } else {
            editTextNumber.setBackgroundResource(R.drawable.backgound_edit)
        }

        if (email.isEmpty() || !email.contains("@")) {
            editTextEmail.setBackgroundResource(R.drawable.background_edit_red)
            isValid = false
        } else {
            editTextEmail.setBackgroundResource(R.drawable.backgound_edit)
        }

        if (password != confirmPassword) {
            isValid = false
        }



        button_singUp.isEnabled = isValid
        button_singUp.setBackgroundColor(if (isValid) 0xFF0560FA.toInt() else 0xFFA7A7A7.toInt())
        button_singUp.setTextColor(0xFFFFFFFF.toInt())
        if (isValid) {
            button_singUp.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

