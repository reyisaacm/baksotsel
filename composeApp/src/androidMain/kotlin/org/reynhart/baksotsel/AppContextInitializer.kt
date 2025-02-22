package org.reynhart.baksotsel

import android.app.Application
import android.content.Context
import androidx.startup.Initializer

internal object AppContext {
    private lateinit var application: Application

    fun setUp(context: Context) {
        application = context as Application
    }

    fun get(): Context {
        if(::application.isInitialized.not()) throw Exception("Application context isn't initialized")
        return application.applicationContext
    }
}

internal lateinit var applicationContext: Context

internal class AppContextInitializer: Initializer<Context>{
    override fun create(context: Context): Context {
        AppContext.setUp(context.applicationContext)
        applicationContext = context.applicationContext
        return AppContext.get()
    }
    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}

