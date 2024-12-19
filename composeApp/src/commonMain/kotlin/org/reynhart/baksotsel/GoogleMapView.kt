package org.reynhart.baksotsel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.Flow
import org.reynhart.baksotsel.models.LoginUserModel

@Composable
expect fun GoogleMapView(
    currentUser: LoginUserModel,
    locList: SnapshotStateList<LoginUserModel>
    )