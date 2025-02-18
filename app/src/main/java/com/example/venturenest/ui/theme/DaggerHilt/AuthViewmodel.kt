package com.example.venturenest.ui.theme.DaggerHilt

import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.venturenest.ui.theme.auth.AuthResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private var user = mutableStateOf(authRepository.getCurrentUser())
    private val _authState = MutableStateFlow<AuthResult>(AuthResult.Loading)
    val authState: StateFlow<AuthResult> = _authState
    var use = authRepository.getCurrentUser()

    fun isUserSignedIn(): Boolean {
        if (user.value == null){
            return false
    }else{

        return true
    }
}
    fun getGoogleSignInIntent(): Intent {
        return authRepository.signInWithGoogleIntent()
    }

    fun signInWithGoogle(data :Intent) {
        viewModelScope.launch {
            _authState.value = AuthResult.Loading
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    _authState.value = authRepository.firebaseAuthWithGoogle(account.idToken!!)
                } else {
                    _authState.value = AuthResult.Error("Google Sign-In failed")
                }
            } catch (e: ApiException) {
                _authState.value = AuthResult.Error("Google Sign-In error: ${e.message}")
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authRepository.signOut()
        }
    }
}
