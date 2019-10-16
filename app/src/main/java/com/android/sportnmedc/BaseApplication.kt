package com.android.sportnmedc

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class BaseApplication  : Application() {
    companion object {
        lateinit var prefs : PreferencesManager
    }

    override fun onCreate() {
        super.onCreate()

        prefs = PreferencesManager(applicationContext)
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}