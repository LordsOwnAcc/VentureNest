package com.example.venturenest.ui.theme.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: Users)

    @Query("SELECT * FROM User")
    fun getUser(): Flow<List<Users>>

    @Delete
    suspend fun deleteUser(users: Users)

    @Update
    suspend fun updateUser(user: Users)
}