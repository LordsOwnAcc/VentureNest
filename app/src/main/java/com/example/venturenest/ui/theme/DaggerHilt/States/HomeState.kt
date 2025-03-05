package com.example.venturenest.ui.theme.DaggerHilt.States

import com.example.venturenest.ui.theme.DaggerHilt.SuccessStories
import java.lang.Error

data class HomeState (
  val state:HomePageCompanion=HomePageCompanion.Loading,
    val result: List<SuccessStories> = emptyList(),
   val error: String? = null
)

sealed class HomePageCompanion(){
    object Loading :HomePageCompanion()
   object Error:HomePageCompanion()
    object Result:HomePageCompanion()
}
