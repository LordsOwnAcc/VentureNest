package com.example.venturenest.ui.theme.DaggerHilt

import android.content.Intent
import com.example.venturenest.ui.theme.auth.AuthResult
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val googleSignInClient: GoogleSignInClient
) {

    suspend fun firebaseAuthWithGoogle(idToken: String): AuthResult {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = firebaseAuth.signInWithCredential(credential).await()
            AuthResult.Success(authResult.user)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Authentication failed")
        }
    }

    fun signInWithGoogleIntent(): Intent {
        return googleSignInClient.signInIntent


    }

    suspend fun signOut() {
        firebaseAuth.signOut()
        googleSignInClient.signOut().await()
    }

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser
}
