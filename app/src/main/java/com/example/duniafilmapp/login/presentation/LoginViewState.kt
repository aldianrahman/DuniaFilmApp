package com.example.duniafilmapp.login.presentation

sealed class LoginViewState {

    object Loading: LoginViewState()

    object Login: LoginViewState()

    object Signup: LoginViewState()

    object Forget: LoginViewState()

    object Error: LoginViewState()
}