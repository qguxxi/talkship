package com.artifee.talkship.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.ui.unit.Dp
import com.artifee.talkship.core.designsystem.theme.TalkshipBodyMid
import com.artifee.talkship.core.designsystem.theme.TalkshipCanvas
import com.artifee.talkship.core.designsystem.theme.TalkshipCanvasCard
import com.artifee.talkship.core.designsystem.theme.TalkshipCardShape
import com.artifee.talkship.core.designsystem.theme.TalkshipHairline
import com.artifee.talkship.core.designsystem.theme.TalkshipInk
import com.artifee.talkship.core.designsystem.theme.TalkshipPillShape
import com.artifee.talkship.core.designsystem.theme.TalkshipPrimary
import com.artifee.talkship.core.designsystem.theme.TalkshipEyebrow as TalkshipEyebrowStyle
import com.artifee.talkship.core.designsystem.theme.TalkshipMonoCaptionSm as TalkshipMonoCaptionSmStyle

@Composable
fun TalkshipResponsiveContainer(
    modifier: Modifier = Modifier,
    maxWidth: Dp = 560.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(max = maxWidth)
                .fillMaxWidth(),
            content = content
        )
    }
}

@Composable
fun TalkshipPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingContent: (@Composable () -> Unit)? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 52.dp),
        enabled = enabled,
        shape = TalkshipPillShape,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = TalkshipPrimary,
            contentColor = TalkshipCanvas,
            disabledContainerColor = TalkshipPrimary.copy(alpha = 0.35f),
            disabledContentColor = TalkshipCanvas.copy(alpha = 0.65f)
        ),
        border = BorderStroke(1.dp, TalkshipPrimary.copy(alpha = if (enabled) 1f else 0.2f))
    ) {
        if (leadingContent != null) {
            leadingContent()
            Spacer(modifier = Modifier.width(10.dp))
        }
        Text(text = text, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun TalkshipOutlineButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingContent: (@Composable () -> Unit)? = null
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 52.dp),
        enabled = enabled,
        shape = TalkshipPillShape,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = TalkshipCanvas,
            contentColor = TalkshipInk
        ),
        border = BorderStroke(1.dp, TalkshipHairline)
    ) {
        if (leadingContent != null) {
            leadingContent()
            Spacer(modifier = Modifier.width(10.dp))
        }
        Text(text = text, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun TalkshipPillTag(text: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = TalkshipPillShape,
        colors = CardDefaults.cardColors(
            containerColor = TalkshipCanvasCard,
            contentColor = TalkshipInk
        ),
        border = BorderStroke(1.dp, TalkshipHairline)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            style = TalkshipMonoCaptionSmStyle,
            color = TalkshipBodyMid,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun TalkshipFeatureCard(
    title: String,
    body: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = TalkshipCardShape,
        colors = CardDefaults.cardColors(
            containerColor = TalkshipCanvasCard,
            contentColor = TalkshipInk
        ),
        border = BorderStroke(1.dp, TalkshipHairline)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = TalkshipInk
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = body,
                style = MaterialTheme.typography.bodyMedium,
                color = TalkshipBodyMid
            )
        }
    }
}

@Composable
fun TalkshipEyebrow(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text.uppercase(),
        modifier = modifier,
        style = TalkshipEyebrowStyle,
        color = TalkshipInk
    )
}

@Composable
fun TalkshipSectionTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium,
        color = TalkshipInk
    )
}

@Composable
fun TalkshipMutedBody(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        color = TalkshipBodyMid
    )
}

@Composable
fun TalkshipStatRow(label: String, value: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = TalkshipMonoCaptionSmStyle,
            color = TalkshipBodyMid
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.labelLarge,
            color = TalkshipInk
        )
    }
}
