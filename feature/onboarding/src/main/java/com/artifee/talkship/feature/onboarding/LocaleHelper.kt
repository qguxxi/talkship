package com.artifee.talkship.feature.onboarding

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

import android.content.ContextWrapper
import androidx.activity.result.ActivityResultRegistryOwner

class LocalizedActivityContext(
    private val baseActivityContext: Context,
    languageCode: String
) : ContextWrapper(createLocalizedContext(baseActivityContext, languageCode)), ActivityResultRegistryOwner {

    override val activityResultRegistry
        get() = (baseActivityContext as? ActivityResultRegistryOwner)?.activityResultRegistry
            ?: error("Base context does not implement ActivityResultRegistryOwner")

    companion object {
        private fun createLocalizedContext(context: Context, languageCode: String): Context {
            val locale = Locale.forLanguageTag(languageCode)
            Locale.setDefault(locale)
            val config = Configuration(context.resources.configuration)
            config.setLocale(locale)
            @Suppress("DEPRECATION")
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
            return context.createConfigurationContext(config)
        }
    }
}

fun updateAppLocale(context: Context, languageCode: String): Context {
    return LocalizedActivityContext(context, languageCode)
}
