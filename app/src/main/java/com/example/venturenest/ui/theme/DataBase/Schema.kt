package com.example.venturenest.ui.theme.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.DaggerHilt.Partners
import com.example.venturenest.ui.theme.DaggerHilt.Patents
import com.example.venturenest.ui.theme.DaggerHilt.StartUp
import com.example.venturenest.ui.theme.DaggerHilt.SuccessStories
import com.example.venturenest.ui.theme.DaggerHilt.councilmembers
import com.example.venturenest.ui.theme.DaggerHilt.heroSection
import com.example.venturenest.ui.theme.DaggerHilt.photo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity(tableName = "User")
data class Users(
    @PrimaryKey val id: Int = 1,
    val name: String,
    val mobileNo :String,
    val dob: String,
    val gender: String,
    val email :String,
    val rollNo:String
)

@Entity(tableName = "AppData")
data class AppData(
    @PrimaryKey val id: Int = 1,
   val events : List<Events>
   ,val heroSection: List<heroSection>,
    val patents: List<Patents>,
    val partner : List<Partners>,
    val sucessStories: List<SuccessStories>,
    val councilmembers: List<councilmembers>,
    val startUp: List<StartUp>,
    val photo: List<photo>
)
class HeroSectionConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromHeroSectionList(list: List<heroSection>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toHeroSectionList(json: String): List<heroSection> {
        val type = object : TypeToken<List<heroSection>>() {}.type
        return gson.fromJson(json, type)
    }
}
class EventSectionConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromEventSectionList(list: List<Events>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toEventSectionList(json: String): List<Events> {
        val type = object : TypeToken<List<Events>>() {}.type
        return gson.fromJson(json, type)
    }
}
class PatentSectionConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromPatentSectionList(list: List<Patents>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toPatentSectionList(json: String): List<Patents> {
        val type = object : TypeToken<List<Patents>>() {}.type
        return gson.fromJson(json, type)
    }
}
class PartnerSectionConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromPartnerSectionList(list: List<Partners>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toPartnerSectionList(json: String): List<Partners> {
        val type = object : TypeToken<List<Partners>>() {}.type
        return gson.fromJson(json, type)
    }
}
class successSectionConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromsuccessSectionList(list: List<SuccessStories>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun tosuccessSectionList(json: String): List<SuccessStories> {
        val type = object : TypeToken<List<SuccessStories>>() {}.type
        return gson.fromJson(json, type)
    }
}
class councilSectionConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromcouncilSectionList(list: List<councilmembers>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun tocouncilSectionList(json: String): List<councilmembers> {
        val type = object : TypeToken<List<councilmembers>>() {}.type
        return gson.fromJson(json, type)
    }
}
class StartupSectionConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromStartupSectionList(list: List<StartUp>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStartupSectionList(json: String): List<StartUp> {
        val type = object : TypeToken<List<StartUp>>() {}.type
        return gson.fromJson(json, type)
    }
}
class photoSectionConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromphotoSectionList(list: List<photo>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun tophotoSectionList(json: String): List<photo> {
        val type = object : TypeToken<List<photo>>() {}.type
        return gson.fromJson(json, type)
    }
}


