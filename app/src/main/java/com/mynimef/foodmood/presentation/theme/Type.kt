package com.mynimef.foodmood.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.GenericFontFamily
import androidx.compose.ui.text.font.ResourceFont
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.sp
import com.mynimef.foodmood.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Font(R.font.rubik).toFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp),

    titleLarge = TextStyle(
        fontFamily = Font(R.font.rubik).toFontFamily(),
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 38.sp,
        letterSpacing = 0.sp,
        color = LightGreen
    ),
    titleMedium = TextStyle(
        fontFamily = Font(R.font.rubik).toFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp,
        lineHeight = 38.sp,
        letterSpacing = 0.sp,
        color = DarkBrown
    ),

    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

)

