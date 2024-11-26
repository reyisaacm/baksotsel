package org.reynhart.baksotsel.models

import kotlinx.serialization.Serializable

@Serializable
class LoginUserModel(
    val name: String,
    val type: String,
    val currentCoordinateLat: Double,
    val currentCoordinateLong: Double
)