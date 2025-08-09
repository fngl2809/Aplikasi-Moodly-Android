package com.fionaanggilia.miniprojek

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun MoodGraphScreen(viewModel: MoodViewModel, navController: NavHostController) {
    val moods = viewModel.moods.collectAsState().value

    val days = listOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")
    val labels = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    val moodToEmoji = mapOf(
        "happy" to R.drawable.mood_happy,
        "sad" to R.drawable.mood_sad,
        "angry" to R.drawable.mood_angry
    )

    val moodLevel = mapOf(
        "happy" to 0,
        "sad" to 2,
        "angry" to 4
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE7C1)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Mood Graph",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .padding(horizontal = 8.dp)
        ) {
            for (i in 0..4) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color(0xFFFFA500))
                        .align(Alignment.TopStart)
                        .offset(y = (i * 60).dp)
                )
            }

            days.forEachIndexed { index, day ->
                val entry = moods[day]
                entry?.let {
                    val emoji = moodToEmoji[it.emojiType] ?: R.drawable.mood_happy
                    val topOffset = (moodLevel[it.emojiType] ?: 2) * 60

                    Image(
                        painter = painterResource(id = emoji),
                        contentDescription = it.emojiType,
                        modifier = Modifier
                            .size(40.dp)
                            .offset(
                                x = (index * 45).dp,
                                y = topOffset.dp
                            )
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            labels.forEach {
                Text(it, fontSize = 12.sp, color = Color.Black)
            }
        }

        // ✅ BOTTOM NAVIGATION (sama seperti MoodSelection)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF004D40))
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.nav_mood),
                contentDescription = "Mood",
                modifier = Modifier
                    .size(50.dp)
                    .clickable { navController.navigate("moodSelection") }
            )
            Image(
                painter = painterResource(R.drawable.nav_history),
                contentDescription = "History",
                modifier = Modifier
                    .size(50.dp)
                    .clickable { navController.navigate("moodWeek") }
            )
            Image(
                painter = painterResource(R.drawable.nav_graph),
                contentDescription = "Graph",
                modifier = Modifier
                    .size(50.dp)
                    .clickable { navController.navigate("moodGraph") }
            )
        }
    }
}
