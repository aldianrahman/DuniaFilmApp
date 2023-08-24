package com.example.duniafilmapp.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {

    private val _viewState = MutableLiveData<LoginViewState>()

    val viewState: LiveData<LoginViewState>
        get() = _viewState

//    fun loadUI(){
//        viewModelScope.launch {
//            _viewState.postValue(LoginViewState.Content)
//        }
//    }

    fun login(){
        viewModelScope.launch {
            _viewState.postValue(LoginViewState.Login)
        }
    }

    fun signup(){
        _viewState.postValue(LoginViewState.Signup)
    }

    fun forget(){
        _viewState.postValue(LoginViewState.Forget)
    }

    fun error(){
        _viewState.postValue(LoginViewState.Error)
    }

    fun loading(){
        _viewState.postValue(LoginViewState.Loading)
    }
}