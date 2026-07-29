package com.artifee.talkship

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivityResultRegistryOwner
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.artifee.talkship.core.designsystem.theme.TalkshipTheme
import com.artifee.talkship.feature.auth.GoogleAuthManager
import com.artifee.talkship.feature.auth.GoogleAuthResult
import com.artifee.talkship.feature.auth.GoogleUserAccount
import com.artifee.talkship.feature.auth.TalkshipSignInRoute
import com.artifee.talkship.feature.onboarding.TalkshipAppLanguageRoute
import com.artifee.talkship.feature.onboarding.TalkshipOnboardingRoute
import com.artifee.talkship.feature.onboarding.TalkshipPermissionRoute
import com.artifee.talkship.feature.onboarding.updateAppLocale
import com.artifee.talkship.ui.screens.TalkshipHomeScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val baseContext = LocalContext.current
            val preferences = remember {
                getSharedPreferences(ONBOARDING_PREFERENCES, Context.MODE_PRIVATE)
            }
            val initialLanguage = remember {
                preferences.getString(KEY_APP_LANGUAGE, "vi") ?: "vi"
            }
            var currentAppLanguage by rememberSaveable { mutableStateOf(initialLanguage) }

            val localizedContext = remember(currentAppLanguage) {
                updateAppLocale(baseContext, currentAppLanguage)
            }

            CompositionLocalProvider(
                LocalContext provides localizedContext,
                LocalActivityResultRegistryOwner provides this
            ) {
                TalkshipTheme {
                    TalkshipApp(
                        onLanguageChanged = { newLanguage ->
                            currentAppLanguage = newLanguage
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun TalkshipApp(
        onLanguageChanged: (String) -> Unit
    ) {
        val preferences = remember {
            getSharedPreferences(ONBOARDING_PREFERENCES, Context.MODE_PRIVATE)
        }

        var isSignedIn by rememberSaveable {
            mutableStateOf(preferences.getBoolean(KEY_SIGNED_IN, false))
        }

        if (isSignedIn) {
            TalkshipHomeScreen(
                onSignOut = {
                    saveSignedIn(false)
                    isSignedIn = false
                }
            )
            return
        }

        val initialPage = remember {
            when {
                preferences.getBoolean(KEY_PERMISSIONS_COMPLETED, false) -> 3
                preferences.getBoolean(KEY_LANGUAGE_COMPLETED, false) -> 2
                preferences.getBoolean(KEY_ONBOARDING_COMPLETED, false) -> 1
                else -> 0
            }
        }

        val pagerState = rememberPagerState(
            initialPage = initialPage,
            pageCount = { 4 }
        )
        val coroutineScope = rememberCoroutineScope()

        if (pagerState.currentPage > 0) {
            BackHandler {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            }
        }

        val context = LocalContext.current
        val googleAuthManager = remember(context) { GoogleAuthManager(context) }
        var googleAccount by remember { mutableStateOf<GoogleUserAccount?>(null) }
        var isGoogleAuthLoading by remember { mutableStateOf(false) }

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = true
        ) { page ->
            when (page) {
                0 -> TalkshipOnboardingRoute(
                    onComplete = {
                        saveOnboardingCompleted()
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    }
                )
                1 -> TalkshipAppLanguageRoute(
                    onComplete = { selectedLanguage ->
                        saveAppLanguage(selectedLanguage)
                        onLanguageChanged(selectedLanguage)
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(2)
                        }
                    },
                    onLanguageSelectionChanged = { newLang ->
                        onLanguageChanged(newLang)
                    }
                )
                2 -> TalkshipPermissionRoute(
                    onComplete = {
                        savePermissionsCompleted()
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(3)
                        }
                    }
                )
                3 -> TalkshipSignInRoute(
                    isLoading = isGoogleAuthLoading,
                    googleAccount = googleAccount,
                    onGoogleSignIn = {
                        coroutineScope.launch {
                            isGoogleAuthLoading = true
                            when (val result = googleAuthManager.signInWithGoogle()) {
                                is GoogleAuthResult.Success -> {
                                    googleAccount = result.account
                                    saveSignedIn(true)
                                    isSignedIn = true
                                }
                                is GoogleAuthResult.Error -> {
                                    // Error logged in GoogleAuthManager
                                }
                                is GoogleAuthResult.Cancelled -> {
                                    // User cancelled
                                }
                            }
                            isGoogleAuthLoading = false
                        }
                    },
                    onEmailContinue = {
                        saveSignedIn(true)
                        isSignedIn = true
                    }
                )
            }
        }
    }

    private fun saveOnboardingCompleted() {
        getSharedPreferences(
            ONBOARDING_PREFERENCES,
            Context.MODE_PRIVATE
        ).edit()
            .putBoolean(KEY_ONBOARDING_COMPLETED, true)
            .apply()
    }

    private fun saveAppLanguage(languageCode: String) {
        getSharedPreferences(
            ONBOARDING_PREFERENCES,
            Context.MODE_PRIVATE
        ).edit()
            .putString(KEY_APP_LANGUAGE, languageCode)
            .putBoolean(KEY_LANGUAGE_COMPLETED, true)
            .apply()
    }

    private fun savePermissionsCompleted() {
        getSharedPreferences(
            ONBOARDING_PREFERENCES,
            Context.MODE_PRIVATE
        ).edit()
            .putBoolean(KEY_PERMISSIONS_COMPLETED, true)
            .apply()
    }

    private fun saveSignedIn(signedIn: Boolean) {
        getSharedPreferences(
            ONBOARDING_PREFERENCES,
            Context.MODE_PRIVATE
        ).edit()
            .putBoolean(KEY_SIGNED_IN, signedIn)
            .apply()
    }

    private companion object {
        const val ONBOARDING_PREFERENCES = "talkship_onboarding"
        const val KEY_ONBOARDING_COMPLETED = "completed"
        const val KEY_LANGUAGE_COMPLETED = "language_completed"
        const val KEY_APP_LANGUAGE = "app_language"
        const val KEY_PERMISSIONS_COMPLETED = "permissions_completed"
        const val KEY_SIGNED_IN = "signed_in"
    }
}
