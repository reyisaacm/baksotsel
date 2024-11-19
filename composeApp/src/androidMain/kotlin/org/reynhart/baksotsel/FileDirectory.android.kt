package org.reynhart.baksotsel

import android.content.Context
import org.koin.compose.getKoin


actual fun getFileDirectory(): String {

    return AppContext.get().applicationInfo.dataDir
}