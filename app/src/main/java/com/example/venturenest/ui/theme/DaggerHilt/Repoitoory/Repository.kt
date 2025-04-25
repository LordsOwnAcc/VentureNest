package com.example.venturenest.ui.theme.DaggerHilt.Repoitoory

import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.DaggerHilt.Interface.getCouncil
import com.example.venturenest.ui.theme.DaggerHilt.LatestNews
import com.example.venturenest.ui.theme.DaggerHilt.Partners
import com.example.venturenest.ui.theme.DaggerHilt.Patents
import com.example.venturenest.ui.theme.DaggerHilt.StartUp
import com.example.venturenest.ui.theme.DaggerHilt.SuccessStories
import com.example.venturenest.ui.theme.DaggerHilt.Interface.getNews
import com.example.venturenest.ui.theme.DaggerHilt.Interface.getPartners
import com.example.venturenest.ui.theme.DaggerHilt.Interface.getPatents
import com.example.venturenest.ui.theme.DaggerHilt.Interface.getStartUps
import com.example.venturenest.ui.theme.DaggerHilt.Interface.getSuccessStories
import com.example.venturenest.ui.theme.DaggerHilt.Interface.getevent
import com.example.venturenest.ui.theme.DaggerHilt.Interface.getphoto
import com.example.venturenest.ui.theme.DaggerHilt.Interface.heroSection
import com.example.venturenest.ui.theme.DaggerHilt.councilmembers
import com.example.venturenest.ui.theme.DaggerHilt.photo
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    private val getevents:getevent,
    private val getPatents: getPatents,
    private val getPartners: getPartners
    , private val getStartUps: getStartUps,
    private val getSuccessStories: getSuccessStories
    , private val getNews: getNews,
    private val getphoto: getphoto,
    private val getheroSection: heroSection
    ,private val getCouncilMember: getCouncil

) {
    suspend fun getheroSection(

    ): Response<List<com.example.venturenest.ui.theme.DaggerHilt.heroSection>> {
        return getheroSection.getHero()
    }
    suspend fun getCouncilmembe(): Response<List<councilmembers>>{
        return getCouncilMember.getMembers()
    }
    suspend fun upcominEvents(


    ): Response<List<Events>> {
        return getevents.getEvents()
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
    suspend fun getphoto(

    ): Response<List<photo>> {
        return getphoto.getphotos()
    }
}