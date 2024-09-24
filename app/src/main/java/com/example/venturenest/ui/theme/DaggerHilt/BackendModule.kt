package com.example.venturenest.ui.theme.DaggerHilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {





    @Provides
    @Singleton
    fun provideOkHttpClient(

    ): OkHttpClient {
        return OkHttpClient.Builder()

            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://e-cell-backend-cgc-j-2.onrender.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideupcominEvents(retrofit: Retrofit): upcominevents {
        return retrofit.create(upcominevents::class.java)
    }
    @Provides
    @Singleton
    fun providePartners(retrofit: Retrofit):getPartners {
        return retrofit.create(getPartners::class.java)
    }
    @Provides
    @Singleton
    fun providePatents(retrofit: Retrofit):getPatents {

        return retrofit.create(getPatents::class.java)
    }
    @Provides
    @Singleton
    fun provideStartUps(retrofit: Retrofit): getStartUps {
        return retrofit.create(getStartUps::class.java)
    }
    @Provides
    @Singleton
    fun provideSuccessStories(retrofit: Retrofit):getSuccessStories {
        return retrofit.create(getSuccessStories::class.java)
    }
    @Provides
    @Singleton
    fun providelatestNews(retrofit: Retrofit):getNews{
        return retrofit.create(getNews::class.java)
    }
    @Provides
    @Singleton
    fun providepastevents(retrofit: Retrofit):pastEvents {
        return retrofit.create(pastEvents::class.java)
    }
}
