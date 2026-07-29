package com.artifee.talkship.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artifee.talkship.R
import com.artifee.talkship.core.designsystem.component.TalkshipFeatureCard
import com.artifee.talkship.core.designsystem.component.TalkshipMutedBody
import com.artifee.talkship.core.designsystem.component.TalkshipOutlineButton
import com.artifee.talkship.core.designsystem.component.TalkshipPillTag
import com.artifee.talkship.core.designsystem.component.TalkshipPrimaryButton
import com.artifee.talkship.core.designsystem.component.TalkshipSectionTitle
import com.artifee.talkship.core.designsystem.component.TalkshipStatRow
import com.artifee.talkship.core.designsystem.theme.TalkshipAccentDusk
import com.artifee.talkship.core.designsystem.theme.TalkshipAccentSunset
import com.artifee.talkship.core.designsystem.theme.TalkshipAccentTwilight
import com.artifee.talkship.core.designsystem.theme.TalkshipBodyMid
import com.artifee.talkship.core.designsystem.theme.TalkshipCanvas
import com.artifee.talkship.core.designsystem.theme.TalkshipCardShape
import com.artifee.talkship.core.designsystem.theme.TalkshipInk
import com.artifee.talkship.core.designsystem.theme.TalkshipTheme

@Composable
fun TalkshipHomeScreen() {
    Scaffold(
        containerColor = TalkshipCanvas
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            TalkshipAccentSunset.copy(alpha = 0.08f),
                            TalkshipAccentDusk.copy(alpha = 0.18f),
                            TalkshipAccentTwilight.copy(alpha = 0.05f),
                            TalkshipCanvas
                        ),
                        radius = 1100f
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.talkship_icon_v1),
                            contentDescription = "Talkship logo",
                            modifier = Modifier.size(48.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Column {
                            androidx.compose.material3.Text(
                                text = "Talkship",
                                style = MaterialTheme.typography.titleLarge,
                                color = TalkshipInk
                            )
                            androidx.compose.material3.Text(
                                text = "android first",
                                style = MaterialTheme.typography.bodySmall,
                                color = TalkshipBodyMid
                            )
                        }
                    }
                    TalkshipPillTag(text = "alpha")
                }

                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    TalkshipPillTag(text = "real-time voice translation")
                    TalkshipSectionTitle(text = "Speak naturally. Ship the meaning.")
                    TalkshipMutedBody(
                        text = "A dark, focused Android experience for live speech translation, transcripts, and conversation history."
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TalkshipPrimaryButton(text = "Start session", onClick = { })
                    TalkshipOutlineButton(text = "See system", onClick = { })
                }

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    TalkshipStatRow(label = "MODE", value = "Two-way translate")
                    TalkshipStatRow(label = "SURFACE", value = "Dark canvas")
                    TalkshipStatRow(label = "SHAPE", value = "Pill + hairline")
                }

                TalkshipFeatureCard(
                    title = "Live translate",
                    body = "Capture speech, resolve context, and render a clean translated reply without breaking the conversation flow."
                )
                TalkshipFeatureCard(
                    title = "Transcript memory",
                    body = "Keep session history readable and searchable with a card-based timeline."
                )
                TalkshipFeatureCard(
                    title = "Custom vocabulary",
                    body = "Let users pin names, jargon, and short phrases so the system stays accurate in real conversations."
                )

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    TalkshipPillTag(text = "design tokens")
                    TalkshipMutedBody(
                        text = "Near-black canvas, white pills, 8 px card radius, monochrome eyebrows, and muted accent color reserved for moments that matter."
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = TalkshipCanvas,
                            shape = TalkshipCardShape
                        )
                        .padding(20.dp)
                ) {
                    androidx.compose.material3.Text(
                        text = "Built to feel deliberate, minimal, and ready for expansion.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = TalkshipInk
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
private fun TalkshipHomeScreenPreview() {
    TalkshipTheme {
        TalkshipHomeScreen()
    }
}
