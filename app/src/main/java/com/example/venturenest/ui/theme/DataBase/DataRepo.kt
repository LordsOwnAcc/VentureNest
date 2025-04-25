package com.example.venturenest.ui.theme.DataBase

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class dataRepo @Inject constructor(
    private val userDao: UserDao
,private val appData: AppdataDao) {


    val getAppdata: Flow<AppData> = appData.getAppdata()
    suspend fun insertAppData(apData: AppData){
        appData.insertOrUpdateAppdata(apData)
    }

    val allUsers: Flow<List<Users>> = userDao.getUser()

     suspend fun insert(users: Users) {
        userDao.insertUser(users)
    }

    suspend fun delete(users: Users) {
        userDao.deleteUser(users)
    }
    suspend fun update(users: Users) {
        userDao.updateUser(users)
    }



}