package com.example.venturenest.ui.theme.DaggerHilt.Module

import android.content.Context
import com.example.venturenest.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule (){

    @Provides
    @Singleton
    fun firebaseAuth():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun gso():GoogleSignInOptions{
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("64723296050-6amk939fjkpsdnltfvstag34a1sqfm3r.apps.googleusercontent.com")
            .requestEmail()
            .build()
        return gso
    }

}