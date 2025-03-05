package com.example.venturenest.ui.theme.DaggerHilt.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.RecipeRepository
import com.example.venturenest.ui.theme.DaggerHilt.States.GalleryState
import com.example.venturenest.ui.theme.DaggerHilt.States.GalleryStateCompanion
import com.example.venturenest.ui.theme.DaggerHilt.States.HomePageCompanion
import com.example.venturenest.ui.theme.DaggerHilt.States.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: RecipeRepository
): ViewModel(){

    val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()



    init {
        fetchSuccess()
    }
    fun fetchSuccess(){
        _state.value=_state.value.copy(
            state = HomePageCompanion.Loading
        )
        viewModelScope.launch {
            try {
                val results = repository.getSuccess()
                if (results.isSuccessful&&results.body()!!.isNotEmpty()){
                    _state.value=_state.value.copy(
                        result = results.body()!!,
                        state = HomePageCompanion.Result

                    )
                }else{
                    _state.value=_state.value.copy(
                        state = HomePageCompanion.Error
                        , error = "Error fetcing from server"
                    )
                }

            }catch(e:Exception){

                _state.value=_state.value.copy(
                    state = HomePageCompanion.Error
                    , error = e.localizedMessage
                )

            }
        }




    }








}