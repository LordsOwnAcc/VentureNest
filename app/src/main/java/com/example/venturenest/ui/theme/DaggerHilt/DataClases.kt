package com.example.venturenest.ui.theme.DaggerHilt

import android.media.Image
import java.util.Date

data class Events(
    val _id : String,
    var eventName :String,
    var eventDate: String,
    var eventTitle :String,
    var imageUrl : String,
    var isStarred : Boolean
)
data class heroSection(
    val id:String,
    val mobile:List<laptopSection>,
    val tablet:List<laptopSection>,
    val laptop:List<laptopSection>,
    val desktop:List<laptopSection>
)
data class laptopSection(
    val filename:String,
    val path:String,
    val id:String,
    val uploadedAt: String
)

data class Patents(
    val _id : String,
    var PatentTitle :String,
    var Inventor: String,
    var ApplicationNo :String,
    var PatentYear : String,
)

data class Partners(
    val _id : String,
    var Name :String,
    var imgpath: String,
    var imgName :String,
    var Category : String
)
data class StartUp(
    val _id : String,
    var StartupName :String,
    var CIN: String,
    var FounderName :String,
    var Website : String,
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
    var FounderImg :String,
    var FounderImgName : String,
    var uploadedAt: String,
    val isStarred: Boolean
)
data class photo(
    val _id : String,
    var imageUrl : String,
    val photoName:String
)
