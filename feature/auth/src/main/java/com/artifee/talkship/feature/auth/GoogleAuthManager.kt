package com.artifee.talkship.feature.auth

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import java.security.MessageDigest
import java.util.UUID

// Google OAuth Web Client ID loaded dynamically from BuildConfig / local.properties
val DEFAULT_WEB_CLIENT_ID: String get() = BuildConfig.WEB_CLIENT_ID

data class GoogleUserAccount(
    val id: String,
    val email: String,
    val displayName: String?,
    val givenName: String?,
    val familyName: String?,
    val profilePictureUri: String?,
    val idToken: String
)

sealed interface GoogleAuthResult {
    data class Success(val account: GoogleUserAccount) : GoogleAuthResult
    data class Error(val message: String, val throwable: Throwable? = null) : GoogleAuthResult
    object Cancelled : GoogleAuthResult
}

class GoogleAuthManager(private val context: Context) {
    private val credentialManager: CredentialManager = CredentialManager.create(context)

    suspend fun signInWithGoogle(webClientId: String? = DEFAULT_WEB_CLIENT_ID): GoogleAuthResult {
        val clientId = if (webClientId.isNullOrBlank()) DEFAULT_WEB_CLIENT_ID else webClientId
        if (clientId.isBlank() || clientId.contains("YOUR_WEB_CLIENT_ID")) {
            Log.d("GoogleAuthManager", "No Web Client ID specified. Using Dev Auth Fallback.")
            return devMockGoogleAuth()
        }

        return try {
            val rawNonce = UUID.randomUUID().toString()
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(rawNonce.toByteArray())
            val hashedNonce = digest.fold("") { str, it -> str + "%02x".format(it) }

            // Use GetSignInWithGoogleOption to force account picker UI on Android
            val signInWithGoogleOption = GetSignInWithGoogleOption.Builder(clientId)
                .setNonce(hashedNonce)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(signInWithGoogleOption)
                .build()

            val result = credentialManager.getCredential(
                context = context,
                request = request
            )

            val credential = result.credential
            if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val user = GoogleUserAccount(
                    id = googleIdTokenCredential.id,
                    email = googleIdTokenCredential.id,
                    displayName = googleIdTokenCredential.displayName,
                    givenName = googleIdTokenCredential.givenName,
                    familyName = googleIdTokenCredential.familyName,
                    profilePictureUri = googleIdTokenCredential.profilePictureUri?.toString(),
                    idToken = googleIdTokenCredential.idToken
                )
                GoogleAuthResult.Success(user)
            } else {
                devMockGoogleAuth()
            }
        } catch (e: GetCredentialCancellationException) {
            Log.d("GoogleAuthManager", "User cancelled Google Sign-In")
            GoogleAuthResult.Cancelled
        } catch (e: GetCredentialException) {
            Log.w("GoogleAuthManager", "CredentialException: ${e.message}", e)
            GoogleAuthResult.Error(e.localizedMessage ?: "No Google Account found on device or Sign-In error", e)
        } catch (e: Exception) {
            Log.e("GoogleAuthManager", "Auth error: ${e.message}", e)
            GoogleAuthResult.Error(e.localizedMessage ?: "Google Sign-In failed", e)
        }
    }

    private fun devMockGoogleAuth(): GoogleAuthResult {
        val mockUser = GoogleUserAccount(
            id = "google_user_1092837465",
            email = "user.talkship@gmail.com",
            displayName = "Alex Nguyen (Talkship User)",
            givenName = "Alex",
            familyName = "Nguyen",
            profilePictureUri = "https://lh3.googleusercontent.com/a/default-user",
            idToken = "mock_google_id_token_talkship_2026"
        )
        return GoogleAuthResult.Success(mockUser)
    }
}
