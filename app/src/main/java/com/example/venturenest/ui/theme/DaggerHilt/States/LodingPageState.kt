package com.example.venturenest.ui.theme.DaggerHilt.States

import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.DataBase.AppData

data class LoadingPageState(
    val state: LoadingPageCompanion= LoadingPageCompanion.Loading,
    val Data : AppData= AppData(
        id = 1,
        events = emptyList(),
        heroSection = emptyList(),
        patents = emptyList(),
        partner = emptyList(),
        sucessStories = emptyList(),
        councilmembers = emptyList(),
        startUp = emptyList(),
        photo = emptyList()
    )
    , val error: String?=null
)


sealed class LoadingPageCompanion() {
    object Loading : LoadingPageCompanion()
object Error: LoadingPageCompanion()
    object Result: LoadingPageCompanion()
}