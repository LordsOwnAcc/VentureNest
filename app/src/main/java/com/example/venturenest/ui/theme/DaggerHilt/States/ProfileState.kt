package com.example.venturenest.ui.theme.DaggerHilt.States

import com.example.venturenest.ui.theme.DataBase.AppData
import com.example.venturenest.ui.theme.DataBase.Users


data class ProfileState(
    val state: ProfilePageCompanion=ProfilePageCompanion.Loading,
    val Data : Users= Users(
        id = 1, name = "", mobileNo = "", dob = "", gender = "", email = "", rollNo = ""
    )
    , val error: String?=null
)


sealed class ProfilePageCompanion {
    object Loading : ProfilePageCompanion()
    object Error: ProfilePageCompanion()
    object Result: ProfilePageCompanion()
    object NoUser: ProfilePageCompanion()

}