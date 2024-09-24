package com.example.venturenest.ui.theme.DaggerHilt

import androidx.compose.foundation.interaction.DragInteraction
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: RecipeRepository
): ViewModel() {

    suspend fun getUpcomingEvents(): Response<List<Events>> {
        return repository.upcominEvents()
    }

    suspend fun getPastEvents(): Response<List<Events>> {
        return repository.getPastEvents()
    }
    suspend fun getNews(): Response<List<LatestNews>> {
        return repository.getNews()
    }
    suspend fun getSuccessStories(): Response<List<SuccessStories>> {
        return repository.getSuccess()
    }
    suspend fun getPartners(): Response<List<Partners>> {
        return repository.getPartners()
    }
    suspend fun getPatents(): Response<List<Patents>> {
        return repository.getPatents()
    }
    suspend fun getStartUps(): Response<List<StartUp>> {
        return repository.getStartups()
    }
}