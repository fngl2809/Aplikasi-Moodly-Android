package com.fionaanggilia.miniprojek

import androidx.compose.ui.graphics.Color

data class MoodEntry(
    val emojiType: String, // ✅ ini nama barunya!
    val reason: String,
    val color: Color
)
