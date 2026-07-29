package com.artifee.talkship.feature.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.artifee.talkship.core.designsystem.theme.TalkshipTheme

data class AppLanguage(
    val code: String,
    val nativeName: String,
    val englishName: String,
    val flag: String
)

val defaultSupportedAppLanguages = listOf(
    AppLanguage("vi", "Tiếng Việt", "Vietnamese", "🇻🇳"),
    AppLanguage("en", "English", "English (US)", "🇺🇸"),
    AppLanguage("ja", "日本語", "Japanese", "🇯🇵"),
    AppLanguage("ko", "한국어", "Korean", "🇰🇷"),
    AppLanguage("zh", "中文", "Simplified Chinese", "🇨🇳"),
    AppLanguage("fr", "Français", "French", "🇫🇷"),
    AppLanguage("es", "Español", "Spanish", "🇪🇸"),
    AppLanguage("de", "Deutsch", "German", "🇩🇪")
)

@Composable
fun TalkshipAppLanguageRoute(
    onComplete: (String) -> Unit,
    onLanguageSelectionChanged: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    TalkshipAppLanguageScreen(
        onSelectLanguage = onComplete,
        onLanguageSelectionChanged = onLanguageSelectionChanged,
        modifier = modifier
    )
}

@Composable
fun TalkshipAppLanguageScreen(
    onSelectLanguage: (String) -> Unit,
    onLanguageSelectionChanged: (String) -> Unit = {},
    initialLanguageCode: String = "vi",
    modifier: Modifier = Modifier
) {
    var selectedLanguageCode by remember { mutableStateOf(initialLanguageCode) }

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
                            TalkshipAccentDusk.copy(alpha = 0.20f),
                            TalkshipAccentSunset.copy(alpha = 0.06f),
                            TalkshipCanvas
                        ),
                        center = Offset(180f, 100f),
                        radius = 1200f
                    )
                )
        ) {
            TalkshipResponsiveContainer {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 20.dp)
                ) {
                    LanguageTopBar()

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(R.string.language_eyebrow).uppercase(),
                    style = TalkshipMonoCaptionSm,
                    color = TalkshipAccentTwilight
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.language_title),
                    style = MaterialTheme.typography.displaySmall,
                    color = TalkshipInk
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.language_body),
                    style = MaterialTheme.typography.bodyMedium,
                    color = TalkshipBodyMid
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(defaultSupportedAppLanguages, key = { it.code }) { language ->
                        val isSelected = language.code == selectedLanguageCode
                        LanguageCard(
                            language = language,
                            isSelected = isSelected,
                            onSelect = {
                                selectedLanguageCode = language.code
                                onLanguageSelectionChanged(language.code)
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                TalkshipPrimaryButton(
                    text = stringResource(R.string.language_continue),
                    onClick = { onSelectLanguage(selectedLanguageCode) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        }
    }
}

@Composable
private fun LanguageTopBar() {
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
                text = stringResource(R.string.language_step_two),
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                style = TalkshipMonoCaptionSm,
                color = TalkshipBodyMid
            )
        }
    }
}

@Composable
private fun LanguageCard(
    language: AppLanguage,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSelect() },
        shape = TalkshipCardShape,
        color = if (isSelected) TalkshipCanvasSoft else TalkshipCanvasCard,
        border = BorderStroke(
            1.dp,
            if (isSelected) TalkshipAccentSunset else TalkshipHairline
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = language.flag,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(
                        text = language.nativeName,
                        style = MaterialTheme.typography.titleMedium,
                        color = TalkshipInk
                    )
                    Text(
                        text = language.englishName,
                        style = MaterialTheme.typography.bodySmall,
                        color = TalkshipBodyMid
                    )
                }
            }

            Surface(
                shape = CircleShape,
                color = if (isSelected) TalkshipAccentSunset else TalkshipCanvasCard,
                border = BorderStroke(
                    1.dp,
                    if (isSelected) TalkshipAccentSunset else TalkshipHairline
                ),
                modifier = Modifier.size(22.dp)
            ) {
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(6.dp)
                            .background(TalkshipCanvas, CircleShape)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
private fun TalkshipAppLanguagePreview() {
    TalkshipTheme {
        TalkshipAppLanguageScreen(onSelectLanguage = {})
    }
}
