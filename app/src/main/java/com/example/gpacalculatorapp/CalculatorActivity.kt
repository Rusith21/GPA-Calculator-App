// CalculatorActivity.kt
package com.example.gpacalculatorapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.gpacalculatorapp.util.GPAHistoryManager

class CalculatorActivity : AppCompatActivity() {

    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var gradeSpinner: Spinner
    private lateinit var creditEditText: EditText
    private lateinit var addSubjectButton: Button
    private lateinit var subjectListLayout: LinearLayout

    private val gradeToPoint = mapOf(
        "A+" to 4.0, "A" to 4.0, "A-" to 3.7,
        "B+" to 3.3, "B" to 3.0, "B-" to 2.7,
        "C+" to 2.3, "C" to 2.0, "C-" to 1.7,
        "D+" to 1.3, "D" to 1.0, "E" to 0.0
    )

    private val subjectList = mutableListOf<Pair<Double, Int>>() // Pair<gradePoint, credit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        gradeSpinner = findViewById(R.id.gradeSpinner)
        creditEditText = findViewById(R.id.creditEditText)
        addSubjectButton = findViewById(R.id.addSubjectButton)
        subjectListLayout = findViewById(R.id.subjectListLayout)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        val grades = gradeToPoint.keys.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, grades)
        gradeSpinner.adapter = adapter

        addSubjectButton.setOnClickListener {
            val grade = gradeSpinner.selectedItem.toString()
            val creditStr = creditEditText.text.toString()
            if (creditStr.isNotEmpty()) {
                val credit = creditStr.toIntOrNull()
                val point = gradeToPoint[grade]
                if (credit != null && point != null) {
                    subjectList.add(Pair(point, credit))
                    addSubjectView(grade, credit)
                    creditEditText.text.clear()
                }
            }
        }

        calculateButton.setOnClickListener {
            if (subjectList.isNotEmpty()) {
                val totalCredits = subjectList.sumOf { it.second }
                val totalPoints = subjectList.sumOf { it.first * it.second }
                val gpa = totalPoints / totalCredits
                val gpaRounded = String.format("%.2f", gpa)
                resultTextView.text = "Your GPA: $gpaRounded"

                // ✅ Save GPA to SharedPreferences
                GPAHistoryManager.saveGPA(this, gpaRounded)

                // Optional: Navigate to Home
                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Add at least one subject", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addSubjectView(grade: String, credit: Int) {
        val view = TextView(this)
        view.text = "Grade: $grade, Credit: $credit"
        subjectListLayout.addView(view)
    }
}
