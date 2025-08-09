package com.fionaanggilia.miniprojek

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MoodViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = MoodDataStore(application)

    private val _moods = MutableStateFlow<Map<String, MoodEntry>>(emptyMap())
    val moods: StateFlow<Map<String, MoodEntry>> = _moods.asStateFlow()

    init {
        loadAllMoods()
    }

    private fun loadAllMoods() {
        val days = listOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")
        viewModelScope.launch {
            days.forEach { day ->
                val moodFlow = dataStore.getMood(day)
                val reasonFlow = dataStore.getReason(day)

                combine(moodFlow, reasonFlow) { mood, reason ->
                    if (mood != null && reason != null) {
                        MoodEntry(
                            emojiType = mood,
                            reason = reason,
                            color = getMoodColor(mood)
                        )
                    } else null
                }.collect { entry ->
                    entry?.let {
                        _moods.update { current ->
                            current.toMutableMap().apply {
                                this[day] = entry
                            }
                        }
                    }
                }
            }
        }
    }

    fun updateMood(day: String, emojiType: String, reason: String) {
        viewModelScope.launch {
            dataStore.saveMood(day, emojiType, reason)
            _moods.update { current ->
                current.toMutableMap().apply {
                    this[day] = MoodEntry(emojiType, reason, getMoodColor(emojiType))
                }
            }
        }
    }

    private fun getMoodColor(type: String): Color {
        return when (type) {
            "happy" -> Color(0xFF4CAF50)
            "sad" -> Color(0xFFFFA500)
            "angry" -> Color(0xFFF44336)
            else -> Color.Gray
        }
    }
}
