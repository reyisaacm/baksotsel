package org.reynhart.baksotsel.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.reynhart.baksotsel.data.interfaces.repository.IStorageRepository
import org.reynhart.baksotsel.getCurrentLocation
import org.reynhart.baksotsel.models.LoginUserModel
import org.reynhart.baksotsel.viewmodels.states.LoginStates
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class LoginViewModel(private val storageRepository: IStorageRepository): ViewModel(){
    private var _eventState : MutableState<LoginStates> = mutableStateOf(LoginStates.Init)
    val eventState: State<LoginStates> = _eventState
    init {

    }

    @OptIn(ExperimentalUuidApi::class)
    fun onJoinClick(name: String, type: String){
        _eventState.value = LoginStates.Loading
        viewModelScope.launch {
            getCurrentLocation().collect{
                val userModel :LoginUserModel = LoginUserModel(id=Uuid.random().toString() ,name=name, type=type, currentCoordinateLat =it.latitude, currentCoordinateLong = it.longitude, lastUpdate = Clock.System.now())
                storageRepository.storeUserData(userModel)
                _eventState.value = LoginStates.Success
            }

        }
    }
}