package com.example.duniafilmapp.home.presentation

sealed class HomeViewState{

    object Loading: HomeViewState()

    object Content: HomeViewState()

    object Error: HomeViewState()
}
