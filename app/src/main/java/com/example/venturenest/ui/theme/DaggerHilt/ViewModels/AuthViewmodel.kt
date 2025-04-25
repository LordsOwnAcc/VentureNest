package com.example.venturenest.ui.theme.DaggerHilt.ViewModels


import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.AuthRepo
import com.example.venturenest.ui.theme.auth.AuthProvider
import com.example.venturenest.ui.theme.auth.AuthState
import com.example.venturenest.ui.theme.auth.AuthStateCompanion
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.internal.throwMissingFieldException
import java.lang.IllegalStateException
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    val repository: AuthRepo
    , private val gso: GoogleSignInOptions
):ViewModel(){
    val _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()


    fun gso() = gso
    init {
        if (repository.firebaseAuth.currentUser !=null){
            _authState.value =_authState.value.copy(
                state = AuthStateCompanion.UserExist
            )
        }else{
            _authState.value =_authState.value.copy(
                state = AuthStateCompanion.NoUser
            )
        }


    }
    fun makeLoading(){
        _authState.value = _authState.value.copy(
            state = AuthStateCompanion.Loading,
            authProvider = AuthProvider.none
        )
    }

    fun onGoogleSuccess(){
        _authState.value = _authState.value.copy(
            state = AuthStateCompanion.UserExist,
            authProvider = AuthProvider.Google
        )
    }
    fun onGoogleFailure(error:String){
        _authState.value = _authState.value.copy(
            state = AuthStateCompanion.Error
            , error = error
        )
    }
    fun createUserWithEmailPassword(email :String,password :String) {
        _authState.value = _authState.value.copy(
            state = AuthStateCompanion.Loading
        )
        repository.firebaseAuth.createUserWithEmailAndPassword(
            email, password
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _authState.value = _authState.value.copy(
                    state = AuthStateCompanion.CreatedUser, authProvider = AuthProvider.EmailPassword
                )
            } else {
                val exception = task.exception
                when (exception) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        _authState.value = _authState.value.copy(
                            state = AuthStateCompanion.Error, error = "Invalid Email/Password"
                        )
                    }

                    is FirebaseAuthUserCollisionException -> {
                        _authState.value = _authState.value.copy(
                            state = AuthStateCompanion.Error, error = "Account found logging you in"
                        )
                    }

                    else -> {
                        _authState.value = _authState.value.copy(
                            state = AuthStateCompanion.Error,
                            error = "Error occurred Please try after some time"
                        )
                    }


                }
            }
        }
    }

    fun logInWithEmailPassword(email: String,password: String) {
        _authState.value = _authState.value.copy(
            state = AuthStateCompanion.Loading
        )
        repository.firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = _authState.value.copy(
                        state = AuthStateCompanion.UserExist
                        , authProvider = AuthProvider.EmailPassword
                    )
                } else {
                    val exception = task.exception
                    when (exception) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            _authState.value = _authState.value.copy(
                                state = AuthStateCompanion.Error, error = "please provide valid Email/Password"
                            )
                        }

                        is FirebaseAuthInvalidUserException -> {
                            _authState.value = _authState.value.copy(
                                state = AuthStateCompanion.Error,
                                error = "User does not exist please sign up"
                            )
                        }

                        else -> {
                            _authState.value = _authState.value.copy(
                                state = AuthStateCompanion.Error,
                                error = exception?.message.toString()
                            )
                        }


                    }
                }
            }

    }


    fun SignOut(){

        try {
            repository.firebaseAuth.signOut()

            _authState.value = _authState.value.copy(
                state = AuthStateCompanion.NoUser,
                authProvider = AuthProvider.none
            )
        }catch (e:Exception){

            _authState.value = _authState.value.copy(
                state = AuthStateCompanion.Error
                , error = "Error occurred while logging out"
            )    }


    }


}
