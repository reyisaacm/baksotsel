package org.reynhart.baksotsel.models

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginUserModel(
    val id: String,
    val name: String,
    val type: String,
    val currentCoordinateLat: Double,
    val currentCoordinateLong: Double,
    val lastUpdate: Instant?,
    val isActive: Boolean = true
)