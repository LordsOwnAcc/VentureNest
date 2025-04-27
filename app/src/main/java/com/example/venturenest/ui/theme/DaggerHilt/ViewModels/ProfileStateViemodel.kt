package com.example.venturenest.ui.theme.DaggerHilt.ViewModels


import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.DaggerHilt.Partners
import com.example.venturenest.ui.theme.DaggerHilt.Patents
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.AuthRepo
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.RecipeRepository
import com.example.venturenest.ui.theme.DaggerHilt.StartUp
import com.example.venturenest.ui.theme.DaggerHilt.States.LoadingPageCompanion
import com.example.venturenest.ui.theme.DaggerHilt.States.LoadingPageState
import com.example.venturenest.ui.theme.DaggerHilt.States.ProfilePageCompanion
import com.example.venturenest.ui.theme.DaggerHilt.States.ProfileState
import com.example.venturenest.ui.theme.DaggerHilt.SuccessStories
import com.example.venturenest.ui.theme.DaggerHilt.councilmembers
import com.example.venturenest.ui.theme.DaggerHilt.heroSection
import com.example.venturenest.ui.theme.DaggerHilt.photo
import com.example.venturenest.ui.theme.DataBase.AppData
import com.example.venturenest.ui.theme.DataBase.Users
import com.example.venturenest.ui.theme.DataBase.dataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull


import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileStateViewmodel @Inject constructor(
    val repository: AuthRepo,
    val databaseRepo: dataRepo
) : ViewModel() {
    val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {

fetchData()

    }

    fun addData(user: Users) {
        viewModelScope.launch {
            try {

                _state.value = _state.value.copy(
                    Data = Users(
                        id = 1, name =user.name, dob = user.dob,
                        mobileNo = user.mobileNo, gender = user.gender, email = user.email, rollNo = user.rollNo
                    )
                )


            } catch (e: Exception) {

                _state.value = _state.value.copy(
                   state = ProfilePageCompanion.Error
                    , error = "Unable to add Data"
                    )


            }


        }
    }


    @SuppressLint("SuspiciousIndentation")
    fun fetchData() {
        _state.value = _state.value.copy(
            state = ProfilePageCompanion.Loading
        )
        viewModelScope.launch {
            try {
     val user = databaseRepo.allUsers
             if (user.count() ==0){
                 _state.value = _state.value.copy(
                     state = ProfilePageCompanion.NoUser,


                 )
             }else{
                 _state.value = _state.value.copy(
                     state= ProfilePageCompanion.Result,
                     Data = user.first()
                 )
             }

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    state = ProfilePageCompanion.Error,

                    error = e.localizedMessage
                )


            }

        }


    }


}




