package com.artifee.talkship.core.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.artifee.talkship.core.designsystem.theme.TalkshipAccentDusk
import com.artifee.talkship.core.designsystem.theme.TalkshipAccentSunset
import com.artifee.talkship.core.designsystem.theme.TalkshipAccentTwilight
import com.artifee.talkship.core.designsystem.theme.TalkshipInk

@Composable
fun TalkshipMark(
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Canvas(
        modifier = modifier
            .then(
                if (contentDescription != null) {
                    Modifier.semantics {
                        this.contentDescription = contentDescription
                    }
                } else {
                    Modifier
                }
            )
    ) {
        val width = size.width
        val height = size.height
        val radius = minOf(width, height) * 0.16f
        val frontTop = height * 0.18f
        val frontLeft = width * 0.12f
        val frontWidth = width * 0.68f
        val frontHeight = height * 0.48f

        drawRoundRect(
            brush = Brush.linearGradient(
                colors = listOf(TalkshipAccentSunset, TalkshipAccentDusk),
                start = Offset(frontLeft, frontTop),
                end = Offset(frontLeft + frontWidth, frontTop + frontHeight)
            ),
            topLeft = Offset(frontLeft, frontTop),
            size = Size(frontWidth, frontHeight),
            cornerRadius = CornerRadius(radius, radius)
        )

        val frontTail = Path().apply {
            moveTo(width * 0.24f, height * 0.60f)
            lineTo(width * 0.20f, height * 0.78f)
            lineTo(width * 0.42f, height * 0.62f)
            close()
        }
        drawPath(
            path = frontTail,
            brush = Brush.linearGradient(
                colors = listOf(TalkshipAccentSunset, TalkshipAccentDusk)
            )
        )

        drawRoundRect(
            color = TalkshipInk.copy(alpha = 0.92f),
            topLeft = Offset(width * 0.42f, height * 0.48f),
            size = Size(width * 0.46f, height * 0.30f),
            cornerRadius = CornerRadius(radius * 0.75f, radius * 0.75f)
        )

        val backTail = Path().apply {
            moveTo(width * 0.74f, height * 0.73f)
            lineTo(width * 0.82f, height * 0.88f)
            lineTo(width * 0.62f, height * 0.76f)
            close()
        }
        drawPath(path = backTail, color = TalkshipInk.copy(alpha = 0.92f))

        drawCircle(
            color = TalkshipAccentTwilight,
            radius = width * 0.025f,
            center = Offset(width * 0.55f, height * 0.63f)
        )
        drawCircle(
            color = TalkshipAccentDusk,
            radius = width * 0.025f,
            center = Offset(width * 0.65f, height * 0.63f)
        )
        drawCircle(
            color = TalkshipAccentSunset,
            radius = width * 0.025f,
            center = Offset(width * 0.75f, height * 0.63f)
        )
    }
}
