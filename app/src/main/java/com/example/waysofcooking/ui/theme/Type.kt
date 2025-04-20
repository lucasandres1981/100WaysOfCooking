package com.example.waysofcooking.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.waysofcooking.R

val Poppins = FontFamily(
    Font(R.font.poppinsregular),
    Font(R.font.poppinsbold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Poppins,
        fontSize = 20.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Poppins,
        fontSize = 24.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Poppins,
        fontSize = 14.sp
    )
)
