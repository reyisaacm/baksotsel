package org.reynhart.baksotsel.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.reynhart.baksotsel.data.interfaces.repository.IStorageRepository
import org.reynhart.baksotsel.models.DialogModel
import org.reynhart.baksotsel.models.LoginUserModel
import org.reynhart.baksotsel.viewmodels.states.MainStates

class MainViewModel(private val storageRepository: IStorageRepository): ViewModel() {
    private var _eventState : MutableState<MainStates> = mutableStateOf(MainStates.Init)
    val eventState: State<MainStates> = _eventState
    lateinit var loginData: Flow<LoginUserModel>

    val dialogOptions = listOf<DialogModel>(
        DialogModel(label="OK", value = "ok", isPrimaryColor = true),
        DialogModel(label="Batal", value = "cancel", isPrimaryColor = false),
    )

    init {
        viewModelScope.launch {
            val retrievedUserModel = storageRepository.getUserData()
            if(retrievedUserModel != null){
                loginData = flow{
                    emit(retrievedUserModel)
                    _eventState.value = MainStates.MapLoaded
                }
            }
        }
    }

    fun onLogoutClick(){
        viewModelScope.launch {
            storageRepository.clearUserData()
            _eventState.value = MainStates.Clear
        }

    }
}