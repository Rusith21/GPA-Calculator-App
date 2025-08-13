package com.example.gpacalculatorapp

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gpacalculatorapp.util.GPAHistoryManager
import java.text.SimpleDateFormat
import java.util.*

class HistoryActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var emptyMessage: TextView
    private val historyItems = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        listView = findViewById(R.id.historyListView)

        // Optional message if history is empty
        emptyMessage = TextView(this).apply {
            text = "No GPA history found"
            textSize = 16f
            setTextColor(resources.getColor(android.R.color.darker_gray))
            setPadding(32, 32, 32, 32)
        }

        val history = GPAHistoryManager.loadGPAHistory(this)
        if (history.isEmpty()) {
            listView.emptyView = emptyMessage
        } else {
            val formattedList = history.map {
                val date = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(Date(it.first))
                "$date → GPA: ${it.second}"
            }
            historyItems.addAll(formattedList)
            listView.adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_list_item_1, historyItems)
        }
    }
}
