package org.reynhart.baksotsel.models

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
class LoginUserModel(
    val id: String,
    val name: String,
    val type: String,
    val currentCoordinateLat: Double,
    val currentCoordinateLong: Double,
    val lastUpdate: Instant?
)