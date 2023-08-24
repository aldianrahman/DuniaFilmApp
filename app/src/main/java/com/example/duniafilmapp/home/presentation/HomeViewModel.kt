package com.example.duniafilmapp.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() {

    private val _viewState = MutableLiveData<HomeViewState>()

    val viewState: LiveData<HomeViewState>
        get() = _viewState

    var step = 0

    fun loadUI() {
        viewModelScope.launch {
            _viewState.postValue(HomeViewState.Content)
//            while (true) {
//                when (step) {
//                    1 -> _viewState.postValue(HomeViewState.Error)
//                    2 -> _viewState.postValue(HomeViewState.Loading)
//                    else -> _viewState.postValue(HomeViewState.Content)
//                    // tambahkan kasus lain jika diperlukan
//                }
//
//                delay(5000) // Tunggu selama 5 detik
//                step++ // Tambahkan step setiap 5 detik
//            }
        }
    }
}