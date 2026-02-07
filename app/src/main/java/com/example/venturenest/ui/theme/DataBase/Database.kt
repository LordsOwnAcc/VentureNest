package com.example.venturenest.ui.theme.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.venturenest.ui.theme.DaggerHilt.Events
import com.example.venturenest.ui.theme.DaggerHilt.Partners
import com.example.venturenest.ui.theme.DaggerHilt.Patents
import com.example.venturenest.ui.theme.DaggerHilt.StartUp
import com.example.venturenest.ui.theme.DaggerHilt.SuccessStories
import com.example.venturenest.ui.theme.DaggerHilt.councilmembers
import com.example.venturenest.ui.theme.DaggerHilt.heroSection
import com.example.venturenest.ui.theme.DaggerHilt.photo

//val events : List<Events>
//,val heroSection: List<heroSection>,
//val patents: List<Patents>,
//val partner : List<Partners>,
//val sucessStories: List<SuccessStories>,
//val councilmembers: List<councilmembers>,
//val startUp: List<StartUp>,
//val photo: List<photo>


@Database(entities = [Users::class
                     , AppData::class], version = 2, exportSchema = false)
@TypeConverters(EventSectionConverter::class
, HeroSectionConverter::class,
    PatentSectionConverter::class,
    PartnerSectionConverter::class,
    successSectionConverter::class,
    councilSectionConverter::class,
    StartupSectionConverter::class,
    photoSectionConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun Appdao(): AppdataDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "book_database"
                )
                    .fallbackToDestructiveMigration() // Clear old data when schema changes
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}