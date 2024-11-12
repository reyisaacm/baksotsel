package org.reynhart.baksotsel.models

import kotlinx.serialization.Serializable

@Serializable
class LoginUserModel(
    val name: String,
    val type: String,
    val currentCoordinateLat: String,
    val currentCoordinateLong: String
)