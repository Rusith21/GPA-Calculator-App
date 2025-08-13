package com.example.gpacalculatorapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gpacalculatorapp.util.GPAHistoryManager

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val latestGpaTextView = findViewById<TextView>(R.id.latestGpaTextView)
        val gpaTrendIcon = findViewById<ImageView>(R.id.gpaTrendIcon)

        val gpaHistory = GPAHistoryManager.loadGPAHistory(this)

        if (gpaHistory.isNotEmpty()) {
            val latestGpa = gpaHistory.lastOrNull()?.second?.toFloatOrNull()
            val prevGpa = if (gpaHistory.size >= 2) gpaHistory[gpaHistory.size - 2].second.toFloatOrNull() else null

            if (latestGpa != null) {
                latestGpaTextView.text = "Latest GPA: %.2f".format(latestGpa)

                val trendIcon = when {
                    prevGpa == null -> R.drawable.ic_trending_flat
                    latestGpa > prevGpa -> R.drawable.ic_trending_up
                    latestGpa < prevGpa -> R.drawable.ic_trending_down
                    else -> R.drawable.ic_trending_flat
                }
                gpaTrendIcon.setImageResource(trendIcon)
            } else {
                latestGpaTextView.text = "Latest GPA: Unknown"
                gpaTrendIcon.setImageResource(R.drawable.ic_trending_flat)
            }
        } else {
            latestGpaTextView.text = "No GPA recorded yet"
            gpaTrendIcon.setImageResource(R.drawable.ic_trending_flat)
        }

        findViewById<LinearLayout>(R.id.calculatorCard).setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.historyCard).setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.settingsCard).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
