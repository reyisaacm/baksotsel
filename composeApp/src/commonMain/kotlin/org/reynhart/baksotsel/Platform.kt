package org.reynhart.baksotsel

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform