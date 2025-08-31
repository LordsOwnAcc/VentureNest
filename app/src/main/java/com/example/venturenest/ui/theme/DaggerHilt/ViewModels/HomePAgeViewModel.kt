package com.example.venturenest.ui.theme.DaggerHilt.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.RecipeRepository
import com.example.venturenest.ui.theme.DaggerHilt.States.GalleryState
import com.example.venturenest.ui.theme.DaggerHilt.States.GalleryStateCompanion
import com.example.venturenest.ui.theme.DaggerHilt.States.HomePageCompanion
import com.example.venturenest.ui.theme.DaggerHilt.States.HomePageResult
import com.example.venturenest.ui.theme.DaggerHilt.States.HomeState
import com.example.venturenest.ui.theme.DataBase.AppData
import com.example.venturenest.ui.theme.DataBase.dataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: RecipeRepository,

    val databaseRepo: dataRepo

): ViewModel(){

    val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()



    init {
        fetchSuccess()
    }
    fun fet() {
        viewModelScope.launch {
            val currentData = databaseRepo.getAppdata.first()
            try {
                val events = repository.upcominEvents()
                val heroSection = repository.getheroSection()
                val patents = repository.getPatents()
                val partner = repository.getPartners()
                val successStories = repository.getSuccess()
                val councilMembers = repository.getCouncilmembe()
                val startUps = repository.getStartups()
                val photos = repository.getphoto()

                // Check if any call is successful and has data
                if (
                    events.isSuccessful && !events.body().isNullOrEmpty() ||
                    heroSection.isSuccessful && !heroSection.body().isNullOrEmpty() ||
                    partner.isSuccessful && !partner.body().isNullOrEmpty() ||
                    patents.isSuccessful && !patents.body().isNullOrEmpty() ||
                    successStories.isSuccessful && !successStories.body().isNullOrEmpty() ||
                    councilMembers.isSuccessful && !councilMembers.body().isNullOrEmpty() ||
                    startUps.isSuccessful && !startUps.body().isNullOrEmpty() ||
                    photos.isSuccessful && !photos.body().isNullOrEmpty()
                ) {
                    val newData = AppData(
                        id = 1,
                        events = events.body().takeIf { !it.isNullOrEmpty() } ?: currentData?.events ?: emptyList(),
                        heroSection = heroSection.body().takeIf { !it.isNullOrEmpty() } ?: currentData?.heroSection ?: emptyList(),
                        patents = patents.body().takeIf { !it.isNullOrEmpty() } ?: currentData?.patents ?: emptyList(),
                        sucessStories = successStories.body().takeIf { !it.isNullOrEmpty() } ?: currentData?.sucessStories ?: emptyList(),
                        startUp = startUps.body().takeIf { !it.isNullOrEmpty() } ?: currentData?.startUp ?: emptyList(),
                        councilmembers = councilMembers.body().takeIf { !it.isNullOrEmpty() } ?: currentData?.councilmembers ?: emptyList(),
                        partner = partner.body().takeIf { !it.isNullOrEmpty() } ?: currentData?.partner ?: emptyList(),
                        photo = photos.body().takeIf { !it.isNullOrEmpty() } ?: currentData?.photo ?: emptyList()
                    )

                    if (newData != currentData) {
                        databaseRepo.insertAppData(newData)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchSuccess(){
        _state.value=_state.value.copy(
            state = HomePageCompanion.Loading
        )
        viewModelScope.launch {
            try {
                val results = repository.getSuccess()
                val result2 = repository.getPartners()
                if (results.isSuccessful&&results.body()!!.isNotEmpty() && result2.isSuccessful && result2.body()!!.isNotEmpty()){
                    _state.value=_state.value.copy(
                        result = HomePageResult(
                            results.body()!!,
                            result2.body()!!
                        ),
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