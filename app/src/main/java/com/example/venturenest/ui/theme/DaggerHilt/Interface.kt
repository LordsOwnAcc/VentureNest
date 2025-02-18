package com.example.venturenest.ui.theme.DaggerHilt


import retrofit2.Response
import retrofit2.http.GET

interface upcominevents {

    @GET("/event/getupcoming")
    suspend fun getEvents(
    ): Response<List<Events>>

}
interface pastEvents {

    @GET("/event/getpast")
    suspend fun getpastEvents(
    ): Response<List<Events>>

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

    @GET("/event/getupcoming")
    suspend fun getSuccesStories(
    ): Response<List<SuccessStories>>

}

interface getNews {

    @GET("/news/get")
    suspend fun getNew(
    ): Response<List<LatestNews>>

}