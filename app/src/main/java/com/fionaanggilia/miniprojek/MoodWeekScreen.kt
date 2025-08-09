package com.fionaanggilia.miniprojek

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun MoodWeekScreen(
    navController: NavHostController,
    viewModel: MoodViewModel
) {
    val moods by viewModel.moods.collectAsState()

    val daysOfWeek = listOf(
        "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFFCF2), Color(0xFFF8F4E6), Color(0xFFE8F5E8))
                )
            )
            .padding(horizontal = 24.dp, vertical = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Mood this week",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D5A3D),
                modifier = Modifier.padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .shadow(8.dp, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD0F0D8))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("✨", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
                    Text(
                        text = "\"If you record your mood every day, it will appear here!\"",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF2D5A3D),
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp
                    )
                }
            }
        }

        items(daysOfWeek) { day ->
            val moodEntry = moods[day]

            MoodCard(
                day = day,
                emojiType = moodEntry?.emojiType ?: "",
                reason = moodEntry?.reason ?: "",
                moodColor = moodEntry?.color ?: Color.LightGray
            )
        }

        item { Spacer(modifier = Modifier.height(40.dp)) }

        item {
            Button(
                onClick = { navController.navigate("moodGraph") },
                shape = CircleShape,
                modifier = Modifier.size(70.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E6259))
            ) {
                Text("→", fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}

@Composable
fun MoodCard(
    day: String,
    emojiType: String,
    reason: String,
    moodColor: Color
) {
    val imageRes = when (emojiType) {
        "happy" -> R.drawable.mood_happy
        "sad" -> R.drawable.mood_sad
        "angry" -> R.drawable.mood_angry
        else -> R.drawable.mood_happy // default image
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .shadow(4.dp, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(moodColor.copy(alpha = 0.2f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = emojiType,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = day.replaceFirstChar { it.uppercase() },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF2D3748),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (reason.isNotBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = reason,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(moodColor, CircleShape)
            )
        }
    }
}
