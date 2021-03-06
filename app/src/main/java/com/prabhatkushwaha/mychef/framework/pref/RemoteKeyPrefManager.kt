package com.prabhatkushwaha.mychef.framework.pref

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This pref class is used to store next remote key to be fetch from server for WordPagingRemoteMediator.kt
 */
@Singleton
class RemoteKeyPrefManager @Inject constructor(@ApplicationContext base: Context) :
    BasePreferenceManager(PREF_NAME, base) {

    fun setNextRemoteKey(nextRemoteKey: String?) {
        editor.putString(KEY_NEXT_REMOTE_KEY, nextRemoteKey).apply()
    }

    fun getNextRemoteKey(): String? {
        return sPrefManager.getString(KEY_NEXT_REMOTE_KEY, null)
    }

    fun setReachedToEnd(end: Boolean) {
        sPrefManager.edit().putBoolean(KEY_REACHED_TO_END, end).apply()
    }

    fun isReachedToEnd(): Boolean {
        return sPrefManager.getBoolean(KEY_REACHED_TO_END, false)
    }

    companion object {
        const val PREF_NAME = "paging_remote_key_pref"
        const val KEY_NEXT_REMOTE_KEY = "next_remote_key"
        const val KEY_REACHED_TO_END = "reached_to_end"
    }
}