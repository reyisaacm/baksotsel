package org.reynhart.baksotsel.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import org.reynhart.baksotsel.data.interfaces.repository.IStorageRepository
import org.reynhart.baksotsel.models.DialogModel
import org.reynhart.baksotsel.models.LoginUserModel
import org.reynhart.baksotsel.ui.screen.Main
import org.reynhart.baksotsel.viewmodels.states.MainStates

class MainViewModel(private val storageRepository: IStorageRepository): ViewModel() {
    private var _eventState : MutableState<MainStates> = mutableStateOf(MainStates.Init)
    val eventState: State<MainStates> = _eventState
    lateinit var loginData: LoginUserModel
    val markerList = mutableStateListOf<LoginUserModel>()

    val dialogOptions = listOf<DialogModel>(
        DialogModel(label="OK", value = "ok", isPrimaryColor = true),
        DialogModel(label="Batal", value = "cancel", isPrimaryColor = false),
    )

    init {
        viewModelScope.launch {
            try {
                val retrievedUserModel = storageRepository.getUserData()
                if(retrievedUserModel != null){
                    loginData = retrievedUserModel
                    markerList.add(loginData)
                    val locList = storageRepository.getUserDataStream()
                    _eventState.value = MainStates.MapLoaded
                    locList.collect{
                        val filteredUserType = it.filter { x-> x.type != loginData.type && x.id != loginData.id }
                        for(user in filteredUserType){
                                val userInList = markerList.firstOrNull{x-> x.id == user.id}
                                if(userInList == null && user.isActive){ //if user does not exist in list and active
                                        markerList.add(user)

                                } else if(userInList != null &&  user.isActive == false){ //if user exist in list and not active
                                    val indexToRemove = markerList.indexOfFirst { x-> x.id == user.id }
                                    markerList.removeAt(indexToRemove)
        //                        markerList.removeIf { x->x.id == user.id }
                                } else  if(userInList != null && user.isActive){ //if user exist and active
                                    val latCompare = (userInList.currentCoordinateLat == user.currentCoordinateLat)
                                    val longCompare = (userInList.currentCoordinateLong == user.currentCoordinateLong)
                                    if(!(latCompare && longCompare)){ // location has changed
                                            val indexToRemove = markerList.indexOfFirst { x-> x.id == user.id }
                                            markerList.removeAt(indexToRemove)
                                            markerList.add(user)

                                    }
                                }

                        }
                  }
                }
            }catch (e: Exception){

            }

        }
    }

    fun onLogoutClick(data: LoginUserModel){
        viewModelScope.launch {
            _eventState.value = MainStates.LoggingOut
            storageRepository.clearUserData(data)
            _eventState.value = MainStates.Clear
        }

    }
}