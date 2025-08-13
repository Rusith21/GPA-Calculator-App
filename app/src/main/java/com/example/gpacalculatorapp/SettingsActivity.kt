package com.example.gpacalculatorapp

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etCourse: EditText
    private lateinit var btnClear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        etName = findViewById(R.id.etName)
        etCourse = findViewById(R.id.etCourse)
        btnClear = findViewById(R.id.btnClearHistory)

        val prefs = getSharedPreferences("profile_settings", Context.MODE_PRIVATE)
        val savedName = prefs.getString("name", "")
        val savedCourse = prefs.getString("course", "")
        etName.setText(savedName)
        etCourse.setText(savedCourse)

        etName.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                prefs.edit().putString("name", etName.text.toString()).apply()
            }
        }

        etCourse.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                prefs.edit().putString("course", etCourse.text.toString()).apply()
            }
        }

        btnClear.setOnClickListener {
            val historyPrefs = getSharedPreferences("gpa_history", Context.MODE_PRIVATE)
            historyPrefs.edit().clear().apply()
            Toast.makeText(this, "GPA history cleared", Toast.LENGTH_SHORT).show()
        }
    }
}
