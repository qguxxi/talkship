package com.artifee.talkship.feature.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

sealed interface AuthResult {
    data class Success(val user: FirebaseUser) : AuthResult
    data class Error(val message: String, val exception: Exception? = null) : AuthResult
}

class FirebaseAuthRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    suspend fun signUpWithEmail(email: String, password: String): AuthResult {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user
            if (user != null) {
                saveUserToFirestore(user)
                AuthResult.Success(user)
            } else {
                AuthResult.Error("Tạo tài khoản thất bại")
            }
        } catch (e: Exception) {
            Log.e("FirebaseAuthRepo", "signUpWithEmail error: ${e.message}", e)
            AuthResult.Error(e.localizedMessage ?: "Tạo tài khoản thất bại", e)
        }
    }

    suspend fun signInWithEmail(email: String, password: String): AuthResult {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
            if (user != null) {
                saveUserToFirestore(user)
                AuthResult.Success(user)
            } else {
                AuthResult.Error("Đăng nhập thất bại")
            }
        } catch (e: Exception) {
            Log.e("FirebaseAuthRepo", "signInWithEmail error: ${e.message}", e)
            AuthResult.Error(e.localizedMessage ?: "Đăng nhập thất bại", e)
        }
    }

    suspend fun signInWithGoogleToken(idToken: String): AuthResult {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            val user = result.user
            if (user != null) {
                saveUserToFirestore(user)
                AuthResult.Success(user)
            } else {
                AuthResult.Error("Đăng nhập bằng Google thất bại")
            }
        } catch (e: Exception) {
            Log.e("FirebaseAuthRepo", "signInWithGoogleToken error: ${e.message}", e)
            AuthResult.Error(e.localizedMessage ?: "Xác thực Google thất bại", e)
        }
    }

    suspend fun saveUserToFirestore(user: FirebaseUser) {
        try {
            val userData = hashMapOf(
                "uid" to user.uid,
                "email" to (user.email ?: ""),
                "displayName" to (user.displayName ?: ""),
                "photoUrl" to (user.photoUrl?.toString() ?: ""),
                "lastLoginAt" to System.currentTimeMillis()
            )
            firestore.collection("users")
                .document(user.uid)
                .set(userData, SetOptions.merge())
                .await()
            Log.d("FirebaseAuthRepo", "User saved to Firestore: ${user.uid}")
        } catch (e: Exception) {
            Log.w("FirebaseAuthRepo", "Error saving user to Firestore: ${e.message}", e)
        }
    }

    fun signOut() {
        auth.signOut()
    }
}
