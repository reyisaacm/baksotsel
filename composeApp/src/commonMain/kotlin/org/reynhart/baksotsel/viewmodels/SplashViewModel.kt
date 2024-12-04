package org.reynhart.baksotsel.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.reynhart.baksotsel.data.interfaces.repository.IStorageRepository
import org.reynhart.baksotsel.viewmodels.states.LoginStates
import org.reynhart.baksotsel.viewmodels.states.SplashStates

class SplashViewModel(private val storageRepository: IStorageRepository): ViewModel() {
    private var _eventState : MutableState<SplashStates> = mutableStateOf(SplashStates.Loading)
    val eventState: State<SplashStates> = _eventState
    init{
        viewModelScope.launch {
            val loginData = storageRepository.getUserData()
            if(loginData != null){
                _eventState.value = SplashStates.LoggedIn
            } else {
                _eventState.value = SplashStates.RequireLogin
            }
        }
    }
}