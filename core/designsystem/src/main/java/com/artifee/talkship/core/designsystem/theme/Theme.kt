package com.artifee.talkship.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun TalkshipTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = TalkshipDarkColorScheme,
        typography = TalkshipTypography,
        shapes = TalkshipShapes,
        content = content
    )
}
