package com.example.venturenest.ui.theme.DaggerHilt.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.RecipeRepository
import com.example.venturenest.ui.theme.DaggerHilt.States.CoulageState
import com.example.venturenest.ui.theme.DaggerHilt.States.CoulageStateCompanion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoulageVieModel @Inject constructor(
    val repository: RecipeRepository
):ViewModel(){
    val _state = MutableStateFlow(CoulageState())
    val state = _state.asStateFlow()

    init {
        fetchHeroState()
    }
    fun fetchHeroState(){
        _state.value =_state.value.copy(
            state = CoulageStateCompanion.Loading
        )
        viewModelScope.launch {
            try {
                val result = repository.getheroSection()
                if(result.isSuccessful && result.body()!!.isNotEmpty()){
                    _state.value =_state.value.copy(
                        state = CoulageStateCompanion.Result,
                        result = result.body()!!.first().laptop
                    )
                }else{
                    _state.value =_state.value.copy(
                        state = CoulageStateCompanion.Error,
                        error = "Failed to connect with server"
                    )
                }
            }catch (e:Exception){

                _state.value =_state.value.copy(
                    state = CoulageStateCompanion.Error,
                    error = "Error : ${e.localizedMessage}"
                )

            }



        }





    }




}