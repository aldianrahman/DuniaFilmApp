package com.example.duniafilmapp.shared

sealed class ScreenRoutes(
    var routes : String
){
    object LoginScreen: ScreenRoutes("Masuk")
    object SignupScreen: ScreenRoutes("Daftar")
    object ForgetPasswordScreen: ScreenRoutes("Lupa Password")
}
