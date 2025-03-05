package com.example.venturenest.ui.theme.DaggerHilt.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.RecipeRepository
import com.example.venturenest.ui.theme.DaggerHilt.States.GalleryState
import com.example.venturenest.ui.theme.DaggerHilt.States.GalleryStateCompanion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GallaryViewModel @Inject constructor(
    val repository: RecipeRepository
):ViewModel(){

    val _state = MutableStateFlow(GalleryState())
    val state = _state.asStateFlow()

    fun changeGrid(){
        _state.value=_state.value.copy(
            isGridSelected = !state.value.isGridSelected
        )
    }

    init {
        fetchPhotos()
    }
    fun fetchPhotos(){
        _state.value=_state.value.copy(
            state = GalleryStateCompanion.Loading
        )
        viewModelScope.launch {
            try {
                val results = repository.getphoto()
                if (results.isSuccessful&&results.body()!!.isNotEmpty()){
                    _state.value=_state.value.copy(
                        result = results.body()!!,
                        state = GalleryStateCompanion.Result

                    )
                }else{
                    _state.value=_state.value.copy(
                        state = GalleryStateCompanion.Error
                        , error = "Error fetcing from server"
                    )
                }

            }catch(e:Exception){

                _state.value=_state.value.copy(
                    state = GalleryStateCompanion.Error
                    , error = e.localizedMessage
                )

            }
        }




    }








}