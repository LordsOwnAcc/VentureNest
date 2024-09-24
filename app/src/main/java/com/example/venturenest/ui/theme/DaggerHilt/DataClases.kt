package com.example.venturenest.ui.theme.DaggerHilt

import android.media.Image
import java.util.Date

data class Events(
    val _id : String,
    var Name :String,
    var image: String,
    var Venue :String,
    var date : Date,
    var time : String,
    var description :String,
    var Registration_From_Link : String
)

data class Patents(
    val _id : String,
    var PatentTitle :String,
    var Inventor: String,
    var ApplicationNo :String,
    var PatentYear : Date,
)

data class Partners(
    val _id : String,
    var Name :String,
    var imagePath: String,
    var imagName :String,
    var Category : Date,
    var uploadedAt : String
)
data class StartUp(
    val _id : String,
    var StartupName :String,
    var CIN: String,
    var FounderName :String,
    var Website : Date,
    var ProductName : String
)
data class LatestNews(
    val _id : String,
    var Title :String,
    var img: String,
    var Content :String
)
data class SuccessStories(
    val _id : String,
    var StartupName :String,
    var StartupAbout: String,
    var FounderName :String,
    var FounderImgName : String,
    var uploadedAt: String
)