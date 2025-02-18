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
    var currentCoordinateLat: Double,
    var currentCoordinateLong: Double,
    var lastUpdate: Instant?,
    var isActive: Boolean = true
)