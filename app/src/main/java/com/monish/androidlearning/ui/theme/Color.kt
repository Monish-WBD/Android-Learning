package com.monish.androidlearning.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal val Blue = Color(0xFF1F4B99)
private val BlueDark = Color(0xFFB7C9FF)
internal val Teal = Color(0xFF0F766E)
private val TealDark = Color(0xFF8ED8D0)
internal val Amber = Color(0xFFB45309)
private val AmberDark = Color(0xFFFFC08A)
private val Ink = Color(0xFF1B1C22)
private val Paper = Color(0xFFF6F7FB)
private val Night = Color(0xFF11131A)
private val NightSurface = Color(0xFF1C2030)

val LightColors = lightColorScheme(
    primary = Blue,
    onPrimary = Color.White,
    secondary = Teal,
    onSecondary = Color.White,
    tertiary = Amber,
    onTertiary = Color.White,
    background = Paper,
    onBackground = Ink,
    surface = Color.White,
    onSurface = Ink,
)

val DarkColors = darkColorScheme(
    primary = BlueDark,
    onPrimary = Color(0xFF06245F),
    secondary = TealDark,
    onSecondary = Color(0xFF003733),
    tertiary = AmberDark,
    onTertiary = Color(0xFF4A2500),
    background = Night,
    onBackground = Color(0xFFE8EAF2),
    surface = NightSurface,
    onSurface = Color(0xFFE8EAF2),
)
