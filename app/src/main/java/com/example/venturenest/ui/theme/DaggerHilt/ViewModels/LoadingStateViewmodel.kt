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
import com.example.venturenest.ui.theme.DaggerHilt.SuccessStories
import com.example.venturenest.ui.theme.DaggerHilt.councilmembers
import com.example.venturenest.ui.theme.DaggerHilt.heroSection
import com.example.venturenest.ui.theme.DaggerHilt.photo
import com.example.venturenest.ui.theme.DataBase.AppData
import com.example.venturenest.ui.theme.DataBase.dataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first


import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadingStateViewmodel @Inject constructor(
    val repository: AuthRepo,
    val datarepo : RecipeRepository,
    val databaseRepo: dataRepo
):ViewModel(){
    val _state = MutableStateFlow(LoadingPageState())
    val state = _state.asStateFlow()

init {
    fetchData()
    addData()

}
    fun addData() {
        viewModelScope.launch {
            val data = databaseRepo.getAppdata.first()
            try {

                        _state.value = _state.value.copy(
                            Data = AppData(
                                id = 1,
                                events = data!!.events,
                                heroSection = data!!.heroSection,
                                photo = data!!.photo,
                                patents = data!!.patents,
                                sucessStories = data!!.sucessStories,
                                councilmembers = data!!.councilmembers,
                                partner = data!!.partner,
                                startUp = data!!.startUp

                            )
                        )





            } catch (e: Exception) {


            }


        }
    }


        @SuppressLint("SuspiciousIndentation")
        fun fetchData(){
 _state.value = _state.value.copy(
     state = LoadingPageCompanion.Loading
 )
        viewModelScope.launch {
            val data = databaseRepo.getAppdata.first()
            try {

                val events = datarepo.upcominEvents()
                val heroSection = datarepo.getheroSection()
                val patents =datarepo.getPatents()
                val partner =datarepo.getPartners()
                val sucessStories =datarepo.getSuccess()
                val councilmembers =datarepo.getCouncilmembe()
                val startUp =datarepo.getStartups()
                val photo =datarepo.getphoto()
                if (events.isSuccessful && !events.body().isNullOrEmpty() ||
                    heroSection.isSuccessful && !heroSection.body().isNullOrEmpty()
                    || partner.isSuccessful && !partner.body().isNullOrEmpty()
                    || patents.isSuccessful && !patents.body().isNullOrEmpty()
                    || sucessStories.isSuccessful && !sucessStories.body().isNullOrEmpty()
                    || councilmembers.isSuccessful && !councilmembers.body().isNullOrEmpty()
                    ||startUp.isSuccessful && !startUp.body().isNullOrEmpty()
                    ||photo.isSuccessful && !photo.body().isNullOrEmpty()){

                    databaseRepo.insertAppData(
                        AppData(
                            id =1,
                            events=events.body()!!
                            , heroSection = heroSection.body()!!,
                            patents=patents.body()!!,
                            sucessStories=sucessStories.body()!!,
                            startUp = startUp.body()!!,
                            councilmembers = councilmembers.body()!!
                            , partner = partner.body()!!, photo = photo.body()!!
                        )

                    )



_state.value = _state.value.copy(
    state = LoadingPageCompanion.Result,
    Data = AppData(
        id = 1,
        events = data!!.events,
        heroSection=data!!.heroSection,
        photo = data!!.photo,
        patents = data!!.patents,
        sucessStories = data!!.sucessStories,
        councilmembers = data!!.councilmembers,
        partner = data!!.partner,
        startUp = data!!.startUp

    )
)

                }else{


                    _state.value = _state.value.copy(
                        state = LoadingPageCompanion.Error,
                        Data = AppData(
                            id = 1,
                            events = data!!.events,
                            heroSection=data!!.heroSection,
                            photo = data!!.photo,
                            patents = data!!.patents,
                            sucessStories = data!!.sucessStories,
                            councilmembers = data!!.councilmembers,
                            partner = data!!.partner,
                            startUp = data!!.startUp

                        )
                    , error = "Check internet connection and try again"
                    )


                }
            }catch (e: Exception){
                _state.value = _state.value.copy(
                    state = LoadingPageCompanion.Error,

                     error = e.localizedMessage
                )


            }

            }



        }












    }




