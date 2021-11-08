package com.prabhatkushwaha.mychef.di

import android.app.Application
import com.prabhatkushwaha.mychef.framework.presentation.PrefManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyChef : Application() {
    private val appPrefManager: PrefManager by lazy {
        return@lazy PrefManager(this)
    }

    override fun onCreate() {
        super.onCreate()
    }

}