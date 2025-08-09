package com.fionaanggilia.miniprojek

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fionaanggilia.miniprojek.R

@Composable
fun MoodSelectionScreen(navController: NavHostController) {
    var selectedMood by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE8F5E8),
                        Color(0xFFD0F0D8),
                        Color(0xFFB8E6C1)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header dengan shadow dan styling yang lebih menarik
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(20.dp)
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.9f)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "How Do You Feel Today?",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32),
                    modifier = Modifier
                        .padding(vertical = 24.dp, horizontal = 16.dp)
                        .fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }

            // Mood selection dengan card dan animasi
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MoodCard(
                    moodType = "happy",
                    moodLabel = "Happy",
                    drawableId = R.drawable.mood_happy,
                    isSelected = selectedMood == "happy",
                    onMoodSelected = { selectedMood = "happy" }
                )

                MoodCard(
                    moodType = "sad",
                    moodLabel = "Sad",
                    drawableId = R.drawable.mood_sad,
                    isSelected = selectedMood == "sad",
                    onMoodSelected = { selectedMood = "sad" }
                )

                MoodCard(
                    moodType = "angry",
                    moodLabel = "Angry",
                    drawableId = R.drawable.mood_angry,
                    isSelected = selectedMood == "angry",
                    onMoodSelected = { selectedMood = "angry" }
                )
            }

            // Button dengan gradient dan shadow
            Button(
                onClick = {
                    if (selectedMood.isNotEmpty()) {
                        navController.navigate("reason/$selectedMood")
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(28.dp)
                    )
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF00695C),
                                Color(0xFF004D40)
                            )
                        ),
                        shape = RoundedCornerShape(28.dp)
                    ),
                enabled = selectedMood.isNotEmpty()
            ) {
                Text(
                    "Continue",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Bottom navigation dengan shadow dan styling yang lebih menarik
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF004D40)
            ),
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavigationItem(
                    drawableId = R.drawable.nav_mood,
                    contentDescription = "Mood",
                    onClick = { navController.navigate("moodSelection") }
                )
                NavigationItem(
                    drawableId = R.drawable.nav_history,
                    contentDescription = "History",
                    onClick = { navController.navigate("moodWeek") }
                )
                NavigationItem(
                    drawableId = R.drawable.nav_graph,
                    contentDescription = "Graph",
                    onClick = { navController.navigate("moodGraph") }
                )
            }
        }
    }
}

@Composable
fun MoodCard(
    moodType: String,
    moodLabel: String,
    drawableId: Int,
    isSelected: Boolean,
    onMoodSelected: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(110.dp)
            .clickable { onMoodSelected() }
            .shadow(
                elevation = if (isSelected) 12.dp else 6.dp,
                shape = RoundedCornerShape(20.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                Color(0xFF81C784) else Color.White.copy(alpha = 0.95f)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) Color.White.copy(alpha = 0.3f)
                        else Color.Transparent
                    )
                    .border(
                        width = if (isSelected) 2.dp else 0.dp,
                        color = if (isSelected) Color.White else Color.Transparent,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(drawableId),
                    contentDescription = moodLabel,
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = moodLabel,
                fontSize = 14.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) Color.White else Color(0xFF2E7D32)
            )
        }
    }
}

@Composable
fun NavigationItem(
    drawableId: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(
                Color.White.copy(alpha = 0.1f)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(drawableId),
            contentDescription = contentDescription,
            modifier = Modifier.size(40.dp)
        )
    }
}