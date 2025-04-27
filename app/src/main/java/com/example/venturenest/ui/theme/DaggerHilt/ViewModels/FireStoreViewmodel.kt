package com.example.venturenest.ui.theme.DaggerHilt.ViewModels

import android.R
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.AuthRepo
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.RecipeRepository
import com.example.venturenest.ui.theme.DataBase.dataRepo
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.count
import javax.inject.Inject
import kotlin.String

@HiltViewModel
class FireStoreViewmodel @Inject constructor(
    val repository: RecipeRepository,
    val authRepo: AuthRepo,
    val dataRepo : dataRepo
) :ViewModel(){





    val db = Firebase.firestore
     val currentUID = authRepo.firebaseAuth.uid

    fun addUser(){
        val user = hashMapOf(
            "uid" to currentUID,
            "name" to "String",
            "mobileNo"  to "String",
            "dob" to "String",
            "gender" to  "String",
            "email" to "String",
            "memberShip" to "String",
            "rollNo" to "String"
        )
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->

            }
            .addOnFailureListener { e ->

            }
    }



}