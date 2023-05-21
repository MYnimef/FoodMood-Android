package com.mynimef.foodmood.presentation.theme

import android.app.Activity
import android.os.Build
import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Green,
    onPrimary = Biege,
    primaryContainer = LightGreen,
    onPrimaryContainer = DarkBrown,

    secondary = Yellow,
    onSecondary = DarkBrown,
    secondaryContainer = LightYellow,
    onSecondaryContainer = DarkBrown,

    tertiary = Blue,
    onTertiary = Color.White,
    tertiaryContainer = LightBlue,
    onTertiaryContainer = DarkBrown,

    surface = Biege,
    surfaceVariant = DarkGreen,
    onSurfaceVariant = DarkBrown,
    surfaceTint = DarkGreen,
    onSurface = DarkGreen,

    background = Biege,
    onBackground = DarkBrown,

    error = Red,
    /* inversePrimary = ,
    inverseSurface = ,
    inverseOnSurface = ,

    error = ,
    onError = ,
    errorContainer = ,
    onErrorContainer = ,
    outline = ,
    outlineVariant = ,
    scrim = ,*/
)

private val LightColorScheme = lightColorScheme(
    primary = Green,
    onPrimary = Biege,
    primaryContainer = LightGreen,
    onPrimaryContainer = DarkBrown,

    secondary = Yellow,
    onSecondary = DarkBrown,
    secondaryContainer = LightYellow,
    onSecondaryContainer = DarkBrown,

    tertiary = Blue,
    onTertiary = Color.White,
    tertiaryContainer = LightBlue,
    onTertiaryContainer = DarkBrown,

    surface = Biege,
    surfaceVariant = DarkGreen,
    onSurfaceVariant = DarkBrown,
    surfaceTint = DarkGreen,
    onSurface = DarkGreen,

    background = Biege,
    onBackground = DarkBrown,

    error = Red,
)

@Composable
fun FoodMoodTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}