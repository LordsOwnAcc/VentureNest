package com.example.venturenest.ui.theme.DaggerHilt.ViewModels

import androidx.lifecycle.ViewModel
import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.DaggerHilt.LatestNews
import com.example.venturenest.ui.theme.DaggerHilt.Partners
import com.example.venturenest.ui.theme.DaggerHilt.Patents
import com.example.venturenest.ui.theme.DaggerHilt.Repoitoory.RecipeRepository
import com.example.venturenest.ui.theme.DaggerHilt.StartUp
import com.example.venturenest.ui.theme.DaggerHilt.SuccessStories
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