package com.editro.dailyplanner.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(
    primary = Gold,
    onPrimary = BackgroundWhite,
    primaryContainer = GoldSoft,
    background = Ivory,
    surface = SurfaceCard,
    onBackground = TextDark,
    onSurface = TextDark,
    error = AmberNotice
)

@Composable
fun DailyPlannerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography,
        content = content
    )
}
