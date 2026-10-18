package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = BioCyanAccent,
    onPrimary = Color(0xFF00363A),
    primaryContainer = BioNavySurfaceVariant,
    onPrimaryContainer = BioCyanAccent,
    secondary = BioTealPrimary,
    onSecondary = Color(0xFF00363D),
    secondaryContainer = Color(0xFF023E8A),
    onSecondaryContainer = Color(0xFFD6F3FF),
    tertiary = BioPurpleBiotech,
    onTertiary = Color.White,
    background = BioNavyBackground,
    onBackground = Color(0xFFE2EBF5),
    surface = BioNavySurface,
    onSurface = Color(0xFFE2EBF5),
    surfaceVariant = BioNavySurfaceVariant,
    onSurfaceVariant = Color(0xFFB0C4DE),
    outline = BioNavyOutline
)

private val LightColorScheme = lightColorScheme(
    primary = BioLightPrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFCCEBF6),
    onPrimaryContainer = Color(0xFF001F29),
    secondary = BioLightSecondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD3E4FF),
    onSecondaryContainer = Color(0xFF001C3B),
    tertiary = BioPurpleBiotech,
    onTertiary = Color.White,
    background = BioLightBackground,
    onBackground = Color(0xFF0D1B2A),
    surface = BioLightSurface,
    onSurface = Color(0xFF0D1B2A),
    surfaceVariant = BioLightSurfaceVariant,
    onSurfaceVariant = Color(0xFF435364),
    outline = BioLightOutline
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep branded bioscience identity consistent
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
