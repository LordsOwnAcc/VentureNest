package com.example.venturenest.ui.theme.DaggerHilt.Module

import com.example.venturenest.ui.theme.DaggerHilt.Interface.getNews
import com.example.venturenest.ui.theme.DaggerHilt.Interface.getPartners
import com.example.venturenest.ui.theme.DaggerHilt.Interface.getPatents
import com.example.venturenest.ui.theme.DaggerHilt.Interface.getStartUps
import com.example.venturenest.ui.theme.DaggerHilt.Interface.getSuccessStories
import com.example.venturenest.ui.theme.DaggerHilt.Interface.getevent
import com.example.venturenest.ui.theme.DaggerHilt.Interface.getphoto
import com.example.venturenest.ui.theme.DaggerHilt.Interface.heroSection
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
            .baseUrl("https://venturenest.onrender.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideEvents(retrofit: Retrofit): getevent {
        return retrofit.create(getevent::class.java)
    }

    @Provides
    @Singleton
    fun providePartners(retrofit: Retrofit): getPartners {
        return retrofit.create(getPartners::class.java)
    }
    @Provides
    @Singleton
    fun providePatents(retrofit: Retrofit): getPatents {

        return retrofit.create(getPatents::class.java)
    }
    @Provides
    @Singleton
    fun provideStartUps(retrofit: Retrofit): getStartUps {
        return retrofit.create(getStartUps::class.java)
    }
    @Provides
    @Singleton
    fun provideSuccessStories(retrofit: Retrofit): getSuccessStories {
        return retrofit.create(getSuccessStories::class.java)
    }
    @Provides
    @Singleton
    fun providelatestNews(retrofit: Retrofit): getNews {
        return retrofit.create(getNews::class.java)
    }
    @Provides
    @Singleton
    fun providepastevents(retrofit: Retrofit): getphoto{
        return retrofit.create(getphoto::class.java)
    }
    @Provides
    @Singleton
    fun provideheroSection(retrofit: Retrofit): heroSection {
        return retrofit.create(heroSection::class.java)
    }
}
