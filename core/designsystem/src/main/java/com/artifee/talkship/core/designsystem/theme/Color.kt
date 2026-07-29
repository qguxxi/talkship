package com.artifee.talkship.core.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

val TalkshipPrimary = Color(0xFFFFFFFF)
val TalkshipOnPrimary = Color(0xFF0A0A0A)
val TalkshipInk = Color(0xFFFFFFFF)
val TalkshipInkHover = Color(0xFFFAFAF7)
val TalkshipBody = Color(0xFFDADBE0)
val TalkshipBodyMid = Color(0xFF7D8187)
val TalkshipMute = Color(0xFF7D8187)
val TalkshipHairline = Color(0xFF212327)
val TalkshipCanvas = Color(0xFF0A0A0A)
val TalkshipCanvasSoft = Color(0xFF1A1C20)
val TalkshipCanvasCard = Color(0xFF191919)
val TalkshipCanvasMid = Color(0xFF363A3F)
val TalkshipAccentSunset = Color(0xFFFF7A17)
val TalkshipAccentSunsetSoft = Color(0xFFFFC285)
val TalkshipAccentDusk = Color(0xFF7C3AED)
val TalkshipAccentTwilight = Color(0xFFC4B5FD)
val TalkshipAccentBreeze = Color(0xFFA0C3EC)
val TalkshipAccentMidnight = Color(0xFF0D1726)
val TalkshipSuccess = Color(0xFF77D6A6)

val TalkshipDarkColorScheme = darkColorScheme(
    primary = TalkshipPrimary,
    onPrimary = TalkshipOnPrimary,
    secondary = TalkshipBody,
    onSecondary = TalkshipCanvas,
    tertiary = TalkshipAccentBreeze,
    background = TalkshipCanvas,
    onBackground = TalkshipInk,
    surface = TalkshipCanvas,
    onSurface = TalkshipInk,
    surfaceVariant = TalkshipCanvasSoft,
    onSurfaceVariant = TalkshipBodyMid,
    outline = TalkshipHairline,
    outlineVariant = TalkshipHairline,
    error = Color(0xFFFF5C77),
    onError = TalkshipCanvas
)
