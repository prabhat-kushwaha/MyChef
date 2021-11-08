package com.prabhatkushwaha.mychef.framework.presentation

import android.content.Context
import com.prabhatkushwaha.mychef.framework.pref.BasePreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefManager @Inject constructor(@ApplicationContext context: Context) :
    BasePreferenceManager(null, context) {
}