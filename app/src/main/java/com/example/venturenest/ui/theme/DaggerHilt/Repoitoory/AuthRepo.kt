package com.example.venturenest.ui.theme.DaggerHilt.Repoitoory

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.InstallIn
import javax.inject.Inject

class AuthRepo @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val googleSignInOptions: GoogleSignInOptions
) {

    fun firebaseAuth() :FirebaseAuth{
        return firebaseAuth
    }

    fun gso():GoogleSignInOptions{
        return googleSignInOptions
    }



}
