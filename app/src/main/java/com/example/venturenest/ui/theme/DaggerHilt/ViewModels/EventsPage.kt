package com.example.venturenest.ui.theme.DaggerHilt.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.RecipeRepository
import com.example.venturenest.ui.theme.DaggerHilt.States.CoulageStateCompanion
import com.example.venturenest.ui.theme.DaggerHilt.States.EventsState
import com.example.venturenest.ui.theme.DaggerHilt.States.EventsStateCompanion
import com.example.venturenest.ui.theme.Navigation.EventsPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsPageViewModel @Inject constructor(
    val repository: RecipeRepository
) :ViewModel(){
val _state = MutableStateFlow(EventsState())
    val state = _state.asStateFlow()
fun fetchEvents(){
    _state.value  = _state.value.copy(
        state = EventsStateCompanion.Loading
    )
    viewModelScope.launch {

        try {
            val result = repository.upcominEvents()
            if(result.isSuccessful && result.body()!!.isNotEmpty()){
                _state.value =_state.value.copy(
                    state =EventsStateCompanion.Result ,
                    result = result.body()!!
                )
            }else{
                _state.value =_state.value.copy(
                    state = EventsStateCompanion.Error,
                    error = "Failed to connect with server"
                )
            }
        }catch (e:Exception){

            _state.value =_state.value.copy(
                state = EventsStateCompanion.Error,
                error = "Error : ${e.localizedMessage}"
            )

        }



    }


}

init {
    fetchEvents()
}


}