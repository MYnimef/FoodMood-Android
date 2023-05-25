package com.mynimef.foodmood.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.sp
import com.mynimef.foodmood.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Font(R.font.rubik).toFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = Font(R.font.rubik).toFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp,
        color = DarkBrown
    ),

    bodySmall = TextStyle(
        fontFamily = Font(R.font.rubik).toFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 17.sp,
        letterSpacing = 0.sp,
        color = DarkBrown
    ),

    titleLarge = TextStyle(
        fontFamily = Font(R.font.rubik).toFontFamily(),
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 38.sp,
        letterSpacing = 0.sp,
        color = Green
    ),

    titleMedium = TextStyle(
        fontFamily = Font(R.font.rubik).toFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 31.sp,
        letterSpacing = 0.sp,
        color = DarkestGreen
    ),

    titleSmall = TextStyle(
        fontFamily = Font(R.font.rubik).toFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 27.sp,
        letterSpacing = 0.sp,
        color = DarkBrown
    ),
)

