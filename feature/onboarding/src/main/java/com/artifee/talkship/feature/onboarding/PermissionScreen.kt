package com.artifee.talkship.feature.onboarding

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.artifee.talkship.core.designsystem.component.TalkshipMark
import com.artifee.talkship.core.designsystem.component.TalkshipOutlineButton
import com.artifee.talkship.core.designsystem.component.TalkshipPrimaryButton
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

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.artifee.talkship.core.designsystem.component.TalkshipResponsiveContainer

@Composable
fun TalkshipPermissionRoute(
    onComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    TalkshipPermissionScreen(
        onContinue = onComplete,
        modifier = modifier
    )
}

@Composable
fun TalkshipPermissionScreen(
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var isMicGranted by remember {
        mutableStateOf(checkPermission(context, Manifest.permission.RECORD_AUDIO))
    }

    var isNotifGranted by remember {
        mutableStateOf(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                checkPermission(context, Manifest.permission.POST_NOTIFICATIONS)
            } else {
                true
            }
        )
    }

    val micLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        isMicGranted = granted
    }

    val notifLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        isNotifGranted = granted
    }

    val multiplePermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        isMicGranted = result[Manifest.permission.RECORD_AUDIO] ?: isMicGranted
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            isNotifGranted = result[Manifest.permission.POST_NOTIFICATIONS] ?: isNotifGranted
        }
    }

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
                        center = Offset(200f, 120f),
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
                    PermissionTopBar()

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .verticalScroll(scrollState),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.permissions_eyebrow).uppercase(),
                            style = TalkshipMonoCaptionSm,
                            color = TalkshipAccentTwilight
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = stringResource(R.string.permissions_title),
                            style = MaterialTheme.typography.displaySmall,
                            color = TalkshipInk
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = stringResource(R.string.permissions_body),
                            style = MaterialTheme.typography.bodyMedium,
                            color = TalkshipBodyMid
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        PermissionCard(
                            title = stringResource(R.string.permission_mic_title),
                            badgeText = stringResource(R.string.permission_mic_badge),
                            description = stringResource(R.string.permission_mic_desc),
                            isPrimaryBadge = true
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            PermissionCard(
                                title = stringResource(R.string.permission_notif_title),
                                badgeText = stringResource(R.string.permission_notif_badge),
                                description = stringResource(R.string.permission_notif_desc),
                                isPrimaryBadge = false
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    TalkshipPrimaryButton(
                        text = if (!isMicGranted) {
                            stringResource(R.string.permissions_grant_all)
                        } else {
                            stringResource(R.string.permissions_continue_to_login)
                        },
                        onClick = {
                            if (!isMicGranted) {
                                val perms = mutableListOf(Manifest.permission.RECORD_AUDIO)
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !isNotifGranted) {
                                    perms.add(Manifest.permission.POST_NOTIFICATIONS)
                                }
                                multiplePermissionLauncher.launch(perms.toTypedArray())
                            } else {
                                onContinue()
                            }
                        },
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
private fun PermissionTopBar() {
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
                text = stringResource(R.string.permissions_step_three),
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                style = TalkshipMonoCaptionSm,
                color = TalkshipBodyMid
            )
        }
    }
}

@Composable
private fun PermissionCard(
    title: String,
    badgeText: String,
    description: String,
    isPrimaryBadge: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = TalkshipCardShape,
        color = TalkshipCanvasCard,
        border = BorderStroke(1.dp, TalkshipHairline)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = TalkshipInk
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Surface(
                        shape = TalkshipPillShape,
                        color = if (isPrimaryBadge) TalkshipAccentSunset.copy(alpha = 0.15f) else TalkshipCanvasSoft,
                        border = BorderStroke(
                            1.dp,
                            if (isPrimaryBadge) TalkshipAccentSunset.copy(alpha = 0.4f) else TalkshipHairline
                        )
                    ) {
                        Text(
                            text = badgeText,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            style = TalkshipMonoCaptionSm,
                            color = if (isPrimaryBadge) TalkshipAccentSunset else TalkshipBodyMid
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = TalkshipBody
            )
        }
    }
}

private fun checkPermission(context: Context, permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
private fun TalkshipPermissionPreview() {
    TalkshipTheme {
        TalkshipPermissionScreen(onContinue = {})
    }
}
