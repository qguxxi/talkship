package com.artifee.talkship.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val TalkshipPillShape = RoundedCornerShape(9999.dp)
val TalkshipCardShape = RoundedCornerShape(8.dp)

val TalkshipShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = TalkshipCardShape,
    medium = TalkshipCardShape,
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp)
)
