package org.reynhart.baksotsel.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.reynhart.baksotsel.data.interfaces.repository.IStorageRepository
import org.reynhart.baksotsel.models.LoginUserModel
import org.reynhart.baksotsel.viewmodels.states.LoginStates

class LoginViewModel(private val storageRepository: IStorageRepository): ViewModel(){
    private var _eventState : MutableState<LoginStates> = mutableStateOf(LoginStates.Init)
    val eventState: State<LoginStates> = _eventState
    init {

    }

    fun onJoinClick(userModel: LoginUserModel){
        _eventState.value = LoginStates.Loading
        viewModelScope.launch {
            storageRepository.storeUserData(userModel)
            _eventState.value = LoginStates.Success
        }
    }
}