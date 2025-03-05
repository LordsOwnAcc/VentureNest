package com.example.venturenest.ui.theme.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "User")
data class Users(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name:String,
    val mobileNo :String,
    val email:String,
    val memberShip:String,
    val rollNo:String,
    val isSelected:Boolean,
    val isBoySelected :Boolean
)