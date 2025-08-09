package com.fionaanggilia.miniprojek

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReasonScreen(
    navController: NavHostController,
    emojiType: String,
    viewModel: MoodViewModel
) {
    var reason by remember { mutableStateOf("") }

    // Ambil nama hari (e.g. "friday")
    val today = LocalDate.now().dayOfWeek
        .getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        .lowercase()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF4E6),
                        Color(0xFFFFE7C1),
                        Color(0xFFFFD54F).copy(alpha = 0.3f)
                    )
                )
            )
    ) {
        // Floating decorative elements
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset(x = 50.dp, y = 100.dp)
                .background(
                    Color(0xFFFFA500).copy(alpha = 0.1f),
                    shape = RoundedCornerShape(50.dp)
                )
        )

        Box(
            modifier = Modifier
                .size(80.dp)
                .offset(x = 300.dp, y = 200.dp)
                .background(
                    Color(0xFF3E6259).copy(alpha = 0.1f),
                    shape = RoundedCornerShape(40.dp)
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Card dengan shadow
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(24.dp)
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.95f)
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    modifier = Modifier.padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Emoji indicator berdasarkan mood yang dipilih
                    val emojiDisplay = when(emojiType) {
                        "happy" -> "😊"
                        "sad" -> "😢"
                        "angry" -> "😠"
                        else -> "🤔"
                    }

                    Text(
                        text = emojiDisplay,
                        fontSize = 48.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "Why do you feel like that today?",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2D4A3E),
                        lineHeight = 36.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Share what's on your mind...",
                        fontSize = 16.sp,
                        color = Color(0xFF666666),
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Enhanced TextField dengan styling yang lebih menarik
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(20.dp)
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                OutlinedTextField(
                    value = reason,
                    onValueChange = { reason = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    placeholder = {
                        Text(
                            "Tell us what happened today...",
                            color = Color(0xFF999999)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Write reason",
                            tint = Color(0xFFFFA500)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color(0xFFFFA500),
                        cursorColor = Color(0xFFFFA500),
                        focusedTextColor = Color(0xFF2D4A3E),
                        unfocusedTextColor = Color(0xFF2D4A3E)
                    ),
                    shape = RoundedCornerShape(16.dp),
                    minLines = 3,
                    maxLines = 5
                )
            }

            // Enhanced Button dengan gradient dan animasi
            Button(
                onClick = {
                    viewModel.updateMood(
                        day = today,
                        emojiType = emojiType,
                        reason = reason
                    )
                    navController.navigate("motivation/$emojiType")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(
                        elevation = 16.dp,
                        shape = RoundedCornerShape(28.dp)
                    )
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF3E6259),
                                Color(0xFF2D4A3E),
                                Color(0xFF1B2E23)
                            )
                        ),
                        shape = RoundedCornerShape(28.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(28.dp)
                    ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Continue",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "→",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
        }

        // Subtle floating action hint
        if (reason.isEmpty()) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 100.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFA500).copy(alpha = 0.9f)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "💭 Express yourself freely",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}