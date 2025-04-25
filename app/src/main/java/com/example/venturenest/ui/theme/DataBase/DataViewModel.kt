package com.example.venturenest.ui.theme.DataBase

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DataViewModel @Inject constructor(private val repository:dataRepo
): ViewModel() {
    val _getallUser = repository.allUsers
    var getUser = _getallUser.asLiveData()
    val data = repository.getAppdata.asLiveData()


   fun insert(users: Users){
       viewModelScope.launch {

           repository.insert(users)
       }
    }

    suspend fun delete(users: Users){
        repository.delete(users)
    }

    fun update(users: Users){
        viewModelScope.launch {
            repository.update(users)
        }

    }


}
