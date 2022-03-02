package com.example.lassi

import android.app.Application
import com.example.lassi.di.GlobalModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class LassiApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@LassiApplication)
            modules(GlobalModules.modules)
        }
    }
}