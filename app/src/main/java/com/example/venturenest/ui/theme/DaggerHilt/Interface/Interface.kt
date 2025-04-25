package com.example.venturenest.ui.theme.DaggerHilt.Interface


import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.DaggerHilt.LatestNews
import com.example.venturenest.ui.theme.DaggerHilt.Partners
import com.example.venturenest.ui.theme.DaggerHilt.Patents
import com.example.venturenest.ui.theme.DaggerHilt.StartUp
import com.example.venturenest.ui.theme.DaggerHilt.States.statiticsselected
import com.example.venturenest.ui.theme.DaggerHilt.SuccessStories
import com.example.venturenest.ui.theme.DaggerHilt.councilmembers
import com.example.venturenest.ui.theme.DaggerHilt.heroSection
import com.example.venturenest.ui.theme.DaggerHilt.photo
import retrofit2.Response
import retrofit2.http.GET

interface getevent {

    @GET("/events")
    suspend fun getEvents(
    ): Response<List<Events>>

}

interface heroSection {

    @GET("/images")
    suspend fun getHero(
    ): Response<List<heroSection>>

}


interface getphoto {

    @GET("/photos")
    suspend fun getphotos(
    ): Response<List<photo>>

}
interface getPatents {

    @GET("/getpatent")
    suspend fun getPatents(
    ): Response<List<Patents>>

}

interface getPartners {

    @GET("/getpartner")
    suspend fun getPartner(
    ): Response<List<Partners>>

}

interface getStartUps {

    @GET("/getstartup")
    suspend fun getStartUp(
    ): Response<List<StartUp>>

}
interface getSuccessStories {

    @GET("/getsuccess")
    suspend fun getSuccesStories(
    ): Response<List<SuccessStories>>

}

interface getNews {

    @GET("/news/get")
    suspend fun getNew(
    ): Response<List<LatestNews>>

}
interface getCouncil{

    @GET("/council-members")
    suspend fun getMembers(
    ): Response<List<councilmembers>>
}