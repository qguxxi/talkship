package com.artifee.talkship.feature.onboarding

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.artifee.talkship.core.designsystem.component.TalkshipMark
import com.artifee.talkship.core.designsystem.component.TalkshipPrimaryButton
import com.artifee.talkship.core.designsystem.component.TalkshipResponsiveContainer
import com.artifee.talkship.core.designsystem.theme.TalkshipAccentDusk
import com.artifee.talkship.core.designsystem.theme.TalkshipAccentSunset
import com.artifee.talkship.core.designsystem.theme.TalkshipAccentTwilight
import com.artifee.talkship.core.designsystem.theme.TalkshipBody
import com.artifee.talkship.core.designsystem.theme.TalkshipBodyMid
import com.artifee.talkship.core.designsystem.theme.TalkshipCanvas
import com.artifee.talkship.core.designsystem.theme.TalkshipCanvasCard
import com.artifee.talkship.core.designsystem.theme.TalkshipCanvasSoft
import com.artifee.talkship.core.designsystem.theme.TalkshipCardShape
import com.artifee.talkship.core.designsystem.theme.TalkshipHairline
import com.artifee.talkship.core.designsystem.theme.TalkshipInk
import com.artifee.talkship.core.designsystem.theme.TalkshipMonoCaptionSm
import com.artifee.talkship.core.designsystem.theme.TalkshipPillShape
import com.artifee.talkship.core.designsystem.theme.TalkshipSuccess
import com.artifee.talkship.core.designsystem.theme.TalkshipTheme

@Composable
fun TalkshipOnboardingRoute(
    onComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    TalkshipOnboardingScreen(
        onContinue = onComplete,
        modifier = modifier
    )
}

@Composable
fun TalkshipOnboardingScreen(
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier,
        containerColor = TalkshipCanvas
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            TalkshipAccentDusk.copy(alpha = 0.24f),
                            TalkshipAccentSunset.copy(alpha = 0.08f),
                            TalkshipCanvas
                        ),
                        center = Offset(160f, 160f),
                        radius = 1100f
                    )
                )
        ) {
            TalkshipResponsiveContainer {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 20.dp)
                ) {
                    OnboardingTopBar()

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .verticalScroll(scrollState),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        WelcomeIllustration()
                        Spacer(modifier = Modifier.height(28.dp))

                        Text(
                            text = stringResource(R.string.onboarding_welcome_eyebrow)
                                .uppercase(),
                            style = TalkshipMonoCaptionSm,
                            color = com.artifee.talkship.core.designsystem.theme.TalkshipAccentTwilight
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = stringResource(R.string.onboarding_welcome_title),
                            style = MaterialTheme.typography.displaySmall,
                            color = TalkshipInk
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = stringResource(R.string.onboarding_welcome_body),
                            style = MaterialTheme.typography.bodyMedium,
                            color = TalkshipBodyMid
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    TalkshipPrimaryButton(
                        text = stringResource(R.string.onboarding_continue),
                        onClick = onContinue,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun OnboardingTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TalkshipMark(
            modifier = Modifier.size(36.dp),
            contentDescription = stringResource(R.string.onboarding_logo_description)
        )
        Surface(
            shape = TalkshipPillShape,
            color = TalkshipCanvasSoft,
            border = BorderStroke(1.dp, TalkshipHairline)
        ) {
            Text(
                text = stringResource(R.string.onboarding_step_one),
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                style = TalkshipMonoCaptionSm,
                color = TalkshipBodyMid
            )
        }
    }
}

@Composable
private fun WelcomeIllustration() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(230.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            TalkshipAccentDusk.copy(alpha = 0.32f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        MessageBubble(
            text = stringResource(R.string.onboarding_welcome_source),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 18.dp),
            highlighted = false
        )
        MessageBubble(
            text = stringResource(R.string.onboarding_welcome_translation),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp),
            highlighted = true
        )

        Surface(
            modifier = Modifier.size(104.dp),
            shape = CircleShape,
            color = TalkshipCanvasCard,
            border = BorderStroke(1.dp, TalkshipHairline)
        ) {
            TalkshipMark(
                modifier = Modifier.padding(22.dp),
                contentDescription = null
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .background(TalkshipCanvasSoft, TalkshipPillShape)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(7.dp)
                    .background(TalkshipSuccess, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.onboarding_welcome_live),
                style = TalkshipMonoCaptionSm,
                color = TalkshipBody
            )
        }
    }
}

@Composable
private fun MessageBubble(
    text: String,
    modifier: Modifier,
    highlighted: Boolean
) {
    Surface(
        modifier = modifier.fillMaxWidth(0.72f),
        shape = TalkshipCardShape,
        color = if (highlighted) TalkshipInk else TalkshipCanvasCard,
        border = if (highlighted) null else BorderStroke(1.dp, TalkshipHairline)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            style = MaterialTheme.typography.bodySmall,
            color = if (highlighted) TalkshipCanvas else TalkshipBody
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
private fun TalkshipOnboardingPreview() {
    TalkshipTheme {
        TalkshipOnboardingScreen(onContinue = {})
    }
}
