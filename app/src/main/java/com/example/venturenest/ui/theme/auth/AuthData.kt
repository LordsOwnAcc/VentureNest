package com.example.venturenest.ui.theme.auth

import com.example.venturenest.ui.theme.DataBase.Users

data class AuthState(
    val state : AuthStateCompanion = AuthStateCompanion.Loading,
    val authProvider : AuthProvider = AuthProvider.none,
    val error:String? = null
){

}

sealed class AuthStateCompanion(){
    object Loading : AuthStateCompanion()
    object NoUser : AuthStateCompanion()
    object CreatedUser:AuthStateCompanion()
    object UserExist : AuthStateCompanion()
    object Error : AuthStateCompanion()
}

sealed class AuthProvider(){
    object Google : AuthProvider()
    object EmailPassword : AuthProvider()
    object Phone : AuthProvider()
    object none : AuthProvider()
}

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
fun isValidPassword(password: String): Boolean {
    val passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{6,}\$".toRegex()
    return password.matches(passwordRegex)
}
