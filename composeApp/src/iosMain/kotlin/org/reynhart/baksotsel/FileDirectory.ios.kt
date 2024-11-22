package org.reynhart.baksotsel

import platform.Foundation.NSHomeDirectory

actual fun getFileDirectory(): String {
    return NSHomeDirectory()
}