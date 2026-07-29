package com.artifee.talkship.core.designsystem.component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.artifee.talkship.core.designsystem.R

@Composable
fun TalkshipMark(
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_talkship_logo),
        contentDescription = contentDescription ?: "Talkship Logo",
        modifier = modifier,
        tint = Color.Unspecified
    )
}
