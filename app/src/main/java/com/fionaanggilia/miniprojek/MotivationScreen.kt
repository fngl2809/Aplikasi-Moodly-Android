package com.fionaanggilia.miniprojek

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun MotivationScreen(navController: NavHostController, mood: String) {
    // 🎯 Tentukan teks dan gambar sesuai mood
    val motivationText = when (mood.lowercase()) {
        "happy" -> "“Your smile today can change someone's day.”"
        "sad" -> "“Even rainy days bring flowers tomorrow.”"
        "angry" -> "“Take a deep breath. Peace is stronger than anger.”"
        else -> "You're doing great today!"
    }

    val feelingText = when (mood.lowercase()) {
        "happy" -> "Feeling happy today?"
        "sad" -> "Feeling sad today?"
        "angry" -> "Feeling angry today?"
        else -> "How are you feeling?"
    }

    val imageRes = when (mood.lowercase()) {
        "happy" -> R.drawable.motivation_illustration
        "sad" -> R.drawable.motivation_sad
        "angry" -> R.drawable.motivation_angry
        else -> R.drawable.motivation_default
    }

    // 🌿 UI Layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0F0D8)) // pastel hijau
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = feelingText,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = motivationText,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Black,
                lineHeight = 26.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            Image(
                painter = painterResource(imageRes),
                contentDescription = "Motivational Image",
                modifier = Modifier
                    .size(300.dp)
                    .padding(bottom = 36.dp)
            )

            Button(
                onClick = { navController.navigate("moodSelection") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E6259)),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text("Finish", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}
