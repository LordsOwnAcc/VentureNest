package com.example.venturenest.ui.theme.DaggerHilt.States

import com.example.venturenest.ui.theme.DaggerHilt.photo
import java.lang.Error

data class GalleryState (
    var isGridSelected :Boolean = false,
    var result:List<photo> = emptyList(),
    val error:String?=null,
    val state :GalleryStateCompanion=GalleryStateCompanion.Loading
)



sealed class GalleryStateCompanion(){
    object Loading : GalleryStateCompanion()
    object Result:GalleryStateCompanion()
    object Error:GalleryStateCompanion()


}