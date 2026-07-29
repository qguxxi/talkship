package com.artifee.talkship.feature.auth

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.artifee.talkship.core.designsystem.component.TalkshipMark
import com.artifee.talkship.core.designsystem.component.TalkshipOutlineButton
import com.artifee.talkship.core.designsystem.component.TalkshipPrimaryButton
import com.artifee.talkship.core.designsystem.component.TalkshipResponsiveContainer
import com.artifee.talkship.core.designsystem.theme.TalkshipAccentDusk
import com.artifee.talkship.core.designsystem.theme.TalkshipAccentSunset
import com.artifee.talkship.core.designsystem.theme.TalkshipAccentTwilight
import com.artifee.talkship.core.designsystem.theme.TalkshipBodyMid
import com.artifee.talkship.core.designsystem.theme.TalkshipCanvas
import com.artifee.talkship.core.designsystem.theme.TalkshipCanvasCard
import com.artifee.talkship.core.designsystem.theme.TalkshipHairline
import com.artifee.talkship.core.designsystem.theme.TalkshipInk
import com.artifee.talkship.core.designsystem.theme.TalkshipMonoCaptionSm
import com.artifee.talkship.core.designsystem.theme.TalkshipTheme

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TalkshipSignInRoute(
    onGoogleSignIn: () -> Unit,
    onEmailContinue: (String) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    googleAccount: GoogleUserAccount? = null,
    onEmailSubmit: ((email: String, pass: String, isSignUp: Boolean) -> Unit)? = null
) {
    TalkshipSignInScreen(
        onGoogleSignIn = onGoogleSignIn,
        onEmailContinue = onEmailContinue,
        onEmailSubmit = onEmailSubmit,
        modifier = modifier,
        isLoading = isLoading,
        errorMessage = errorMessage,
        googleAccount = googleAccount
    )
}

@Composable
fun TalkshipSignInScreen(
    onGoogleSignIn: () -> Unit,
    onEmailContinue: (String) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    googleAccount: GoogleUserAccount? = null,
    onEmailSubmit: ((email: String, pass: String, isSignUp: Boolean) -> Unit)? = null
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isSignUpMode by rememberSaveable { mutableStateOf(false) }

    val emailIsValid = email.trim().let {
        it.contains("@") && it.substringAfter("@").contains(".")
    }
    val passwordIsValid = password.length >= 6
    val formIsValid = emailIsValid && passwordIsValid
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
                    Brush.radialGradient(
                        colors = listOf(
                            TalkshipAccentDusk.copy(alpha = 0.20f),
                            TalkshipAccentSunset.copy(alpha = 0.06f),
                            TalkshipCanvas
                        ),
                        center = Offset(160f, 120f),
                        radius = 1050f
                    )
                )
        ) {
            TalkshipResponsiveContainer {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                        .verticalScroll(scrollState)
                ) {
                    Surface(
                        modifier = Modifier.size(52.dp),
                        shape = CircleShape,
                        color = TalkshipCanvasCard,
                        border = BorderStroke(1.dp, TalkshipHairline)
                    ) {
                        TalkshipMark(
                            modifier = Modifier.padding(11.dp),
                            contentDescription = stringResource(
                                R.string.sign_in_logo_description
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(48.dp))
                    Text(
                        text = stringResource(R.string.sign_in_eyebrow).uppercase(),
                        style = TalkshipMonoCaptionSm,
                        color = TalkshipAccentTwilight
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = if (isSignUpMode) "Tạo tài khoản mới" else stringResource(R.string.sign_in_title),
                        style = MaterialTheme.typography.displaySmall,
                        color = TalkshipInk
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(R.string.sign_in_body),
                        style = MaterialTheme.typography.bodyMedium,
                        color = TalkshipBodyMid
                    )

                    if (!errorMessage.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.small,
                            color = TalkshipAccentSunset.copy(alpha = 0.15f),
                            border = BorderStroke(1.dp, TalkshipAccentSunset.copy(alpha = 0.4f))
                        ) {
                            Text(
                                text = errorMessage,
                                modifier = Modifier.padding(12.dp),
                                style = MaterialTheme.typography.bodySmall,
                                color = TalkshipInk,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(36.dp))

                    if (googleAccount != null) {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium,
                            color = TalkshipCanvasCard,
                            border = BorderStroke(1.dp, TalkshipAccentSunset.copy(alpha = 0.5f))
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    modifier = Modifier.size(40.dp),
                                    shape = CircleShape,
                                    color = TalkshipAccentSunset.copy(alpha = 0.2f)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = (googleAccount.displayName?.firstOrNull() ?: 'G').toString(),
                                            style = MaterialTheme.typography.titleMedium,
                                            color = TalkshipInk
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(14.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = googleAccount.displayName ?: "Google User",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = TalkshipInk
                                    )
                                    Text(
                                        text = googleAccount.email,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = TalkshipBodyMid
                                    )
                                }
                                Text(
                                    text = "✓",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = TalkshipAccentSunset
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    TalkshipOutlineButton(
                        text = if (isLoading) "Đang kết nối Google..." else stringResource(R.string.sign_in_google),
                        onClick = onGoogleSignIn,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isLoading,
                        leadingContent = {
                            Image(
                                painter = painterResource(R.drawable.ic_google),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = TalkshipHairline
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = stringResource(R.string.sign_in_or).uppercase(),
                            style = TalkshipMonoCaptionSm,
                            color = TalkshipBodyMid
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = TalkshipHairline
                        )
                    }

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = {
                            Text(stringResource(R.string.sign_in_email_label))
                        },
                        placeholder = {
                            Text(stringResource(R.string.sign_in_email_placeholder))
                        },
                        shape = MaterialTheme.shapes.medium,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TalkshipInk,
                            unfocusedTextColor = TalkshipInk,
                            focusedBorderColor = TalkshipInk,
                            unfocusedBorderColor = TalkshipHairline,
                            focusedLabelColor = TalkshipInk,
                            unfocusedLabelColor = TalkshipBodyMid,
                            cursorColor = TalkshipInk
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        label = {
                            Text(stringResource(R.string.sign_in_password_label))
                        },
                        placeholder = {
                            Text(stringResource(R.string.sign_in_password_placeholder))
                        },
                        shape = MaterialTheme.shapes.medium,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TalkshipInk,
                            unfocusedTextColor = TalkshipInk,
                            focusedBorderColor = TalkshipInk,
                            unfocusedBorderColor = TalkshipHairline,
                            focusedLabelColor = TalkshipInk,
                            unfocusedLabelColor = TalkshipBodyMid,
                            cursorColor = TalkshipInk
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    TalkshipPrimaryButton(
                        text = if (isLoading) "Đang xử lý..." else if (isSignUpMode) stringResource(R.string.sign_up_continue) else stringResource(R.string.sign_in_continue),
                        onClick = {
                            val trimmedEmail = email.trim()
                            val trimmedPass = password.trim()
                            if (onEmailSubmit != null) {
                                onEmailSubmit(trimmedEmail, trimmedPass, isSignUpMode)
                            } else {
                                onEmailContinue(trimmedEmail)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = formIsValid && !isLoading
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    TalkshipOutlineButton(
                        text = if (isSignUpMode) stringResource(R.string.sign_in_mode_signup) else stringResource(R.string.sign_in_mode_login),
                        onClick = { isSignUpMode = !isSignUpMode },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.weight(1f, fill = false))
                    Text(
                        text = stringResource(R.string.sign_in_terms),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = TalkshipBodyMid
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
private fun TalkshipSignInPreview() {
    TalkshipTheme {
        TalkshipSignInScreen(
            onGoogleSignIn = {},
            onEmailContinue = {}
        )
    }
}
