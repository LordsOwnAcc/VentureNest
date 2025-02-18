package com.example.venturenest.ui.theme.DaggerHilt

import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    private val upcominevents: upcominevents,
    private val getPatents: getPatents,
    private val getPartners: getPartners
    ,private val getStartUps: getStartUps,
    private val getSuccessStories: getSuccessStories
    ,private val getNews: getNews,
    private val getpastEvents: pastEvents

) {
    suspend fun upcominEvents(

    ): Response<List<Events>> {
        return upcominevents.getEvents()
    }
    suspend fun getPatents(

    ): Response<List<Patents>> {
        return getPatents.getPatents()
    }


    suspend fun getPartners(

    ): Response<List<Partners>> {
        return getPartners.getPartner()
    }
    suspend fun getStartups(

    ): Response<List<StartUp>> {
        return getStartUps.getStartUp()
    }
    suspend fun getNews(

    ): Response<List<LatestNews>> {
        return getNews.getNew()
    }
    suspend fun getSuccess(

    ): Response<List<SuccessStories>> {
        return getSuccessStories.getSuccesStories()
    }
    suspend fun getPastEvents(

    ): Response<List<Events>> {
        return getpastEvents.getpastEvents()
    }
}