package com.example.gpacalculatorapp.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GPAHistoryManager {
    private const val PREF_NAME = "gpa_prefs"
    private const val HISTORY_KEY = "gpa_history"

    fun saveGPA(context: Context, gpa: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val historyJson = prefs.getString(HISTORY_KEY, "[]")
        val type = object : TypeToken<MutableList<Pair<Long, String>>>() {}.type
        val history: MutableList<Pair<Long, String>> = gson.fromJson(historyJson, type)

        history.add(Pair(System.currentTimeMillis(), gpa))

        val updatedJson = gson.toJson(history)
        prefs.edit().putString(HISTORY_KEY, updatedJson).apply()
    }

    fun loadGPAHistory(context: Context): List<Pair<Long, String>> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val historyJson = prefs.getString(HISTORY_KEY, "[]")
        val type = object : TypeToken<List<Pair<Long, String>>>() {}.type
        return gson.fromJson(historyJson, type)
    }
}
