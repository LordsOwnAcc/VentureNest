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
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull


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
        viewModelScope.launch {
            val data = databaseRepo.getAppdata.firstOrNull()
            
            // Cache expiry time: 1 hour (3600000 milliseconds)
            // You can adjust this value: 
            // - 30 minutes = 1800000
            // - 1 hour = 3600000
            // - 6 hours = 21600000
            // - 24 hours = 86400000
            val CACHE_EXPIRY_MS = 3600000L // 1 hour
            val currentTime = System.currentTimeMillis()
            val isCacheExpired = data?.lastUpdated?.let { 
                (currentTime - it) > CACHE_EXPIRY_MS 
            } ?: true

            if (data == null || data.heroSection.isNullOrEmpty() || isCacheExpired) {
                // Fetch fresh data from backend if:
                // 1. No cached data exists
                // 2. Hero section is empty (corrupted cache)
                // 3. Cache is older than expiry time
                fetchData()
                addData()
            } else {
                // Use cached data if it's fresh
                _state.value = _state.value.copy(
                    state = LoadingPageCompanion.Result,
                    Data = AppData(
                        id = 1,
                        events = data.events,
                        heroSection = data.heroSection,
                        photo = data.photo,
                        patents = data.patents,
                        sucessStories = data.sucessStories,
                        councilmembers = data.councilmembers,
                        partner = data.partner,
                        startUp = data.startUp,
                        lastUpdated = data.lastUpdated
                    )
                )
            }
        }
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
                                startUp = data!!.startUp,
                                lastUpdated = data!!.lastUpdated

                            )
                        )





            } catch (e: Exception) {


            }


        }
    }


    @SuppressLint("SuspiciousIndentation")
    fun fetchData() {
        _state.value = _state.value.copy(state = LoadingPageCompanion.Loading)

        viewModelScope.launch {
            val currentData = databaseRepo.getAppdata.firstOrNull() // might be null on first launch

            try {
                // Launch all network calls in parallel
                val eventsDeferred = async { datarepo.upcominEvents() }
                val heroSectionDeferred = async { datarepo.getheroSection() }
                val patentsDeferred = async { datarepo.getPatents() }
                val partnersDeferred = async { datarepo.getPartners() }
                val successDeferred = async { datarepo.getSuccess() }
                val councilDeferred = async { datarepo.getCouncilmembe() }
                val startupsDeferred = async { datarepo.getStartups() }
                val photosDeferred = async { datarepo.getphoto() }

                val events = eventsDeferred.await()
                val heroSection = heroSectionDeferred.await()
                val patents = patentsDeferred.await()
                val partner = partnersDeferred.await()
                val successStories = successDeferred.await()
                val councilMembers = councilDeferred.await()
                val startUps = startupsDeferred.await()
                val photos = photosDeferred.await()

                // Extract body from each response or fallback to empty list
                val eventsData = events.body().orEmpty()
                val heroData = heroSection.body().orEmpty()
                val patentsData = patents.body().orEmpty()
                val partnersData = partner.body().orEmpty()
                val successData = successStories.body().orEmpty()
                val councilData = councilMembers.body().orEmpty()
                val startupData = startUps.body().orEmpty()
                val photoData = photos.body().orEmpty()

                val isDataAvailable = listOf(
                    eventsData, heroData, patentsData, partnersData,
                    successData, councilData, startupData, photoData
                ).any { it.isNotEmpty() }

                if (isDataAvailable) {
                    val newData = AppData(
                        id = 1,
                        events = eventsData,
                        heroSection = heroData,
                        patents = patentsData,
                        sucessStories = successData,
                        councilmembers = councilData,
                        partner = partnersData,
                        startUp = startupData,
                        photo = photoData,
                        lastUpdated = System.currentTimeMillis() // Save current timestamp
                    )

                    databaseRepo.insertAppData(newData)

                    _state.value = _state.value.copy(
                        state = LoadingPageCompanion.Result,
                        Data = newData
                    )
                } else {
                    // If all data is empty, show error
                    _state.value = _state.value.copy(
                        state = LoadingPageCompanion.Error,
                        error = "No data received from server."
                    )
                }

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    state = LoadingPageCompanion.Error,

                    error = "Network error: ${e.localizedMessage ?: "Unknown error"}"
                )
            }
        }
    }












}




