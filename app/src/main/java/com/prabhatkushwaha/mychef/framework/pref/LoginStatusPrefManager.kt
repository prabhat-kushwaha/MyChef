package com.prabhatkushwaha.mychef.framework.pref

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This pref class is used to store next remote key to be fetch from server for WordPagingRemoteMediator.kt
 */
@Singleton
class LoginStatusPrefManager @Inject constructor(@ApplicationContext base: Context) :
    BasePreferenceManager(PREF_NAME, base) {

    fun setIsNewUser(isNewUser: Boolean) {
        editor.putBoolean(IS_NEW_USER, isNewUser).apply()
    }

    fun getIsNewUser(): Boolean {
        return sPrefManager.getBoolean(IS_NEW_USER, true)
    }

    fun setIsSignIn(status: Boolean) {
        editor.putBoolean(IS_LOGGED_IN, status).apply()
    }

    fun getIsSignIn(): Boolean {
        return sPrefManager.getBoolean(IS_LOGGED_IN, false)
    }

    companion object {
        const val PREF_NAME = "login_status_pref_manager"
        const val IS_NEW_USER = "is_new_user"
        const val IS_LOGGED_IN = "is_logged_in"
    }
}