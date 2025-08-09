package com.fionaanggilia.miniprojek

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "mood_data")

class MoodDataStore(private val context: Context) {

    fun getMood(day: String): Flow<String?> {
        val key = stringPreferencesKey("mood_$day")
        return context.dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    fun getReason(day: String): Flow<String?> {
        val key = stringPreferencesKey("reason_$day")
        return context.dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    suspend fun saveMood(day: String, mood: String, reason: String) {
        val moodKey = stringPreferencesKey("mood_$day")
        val reasonKey = stringPreferencesKey("reason_$day")
        context.dataStore.edit { preferences ->
            preferences[moodKey] = mood
            preferences[reasonKey] = reason
        }
    }
}
