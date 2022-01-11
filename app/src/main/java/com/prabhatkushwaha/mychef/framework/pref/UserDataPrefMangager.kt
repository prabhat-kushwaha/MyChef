package com.prabhatkushwaha.mychef.framework.pref

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserDataPrefManager @Inject constructor(@ApplicationContext context: Context) :
    BasePreferenceManager(PREF_NAME, context) {


    fun saveUserData(user: FirebaseUser?): Boolean {
        if (user != null) {
            editor.putString(USER_NAME, user.displayName).apply()
            editor.putString(EMAIL, user.email).apply()
            editor.putString(ID, user.uid).apply()
            editor.putString(PROFILE_IMAGE, user.photoUrl.toString()).apply()
        }

        return sPrefManager.contains(EMAIL) && sPrefManager.contains(
            ID
        )
    }

    fun getUserName(): String? {
        return sPrefManager.getString(USER_NAME, "")
    }

    fun getUserEmail(): String? {
        return sPrefManager.getString(EMAIL, "")
    }

    fun getProfileImage(): String? {
        return sPrefManager.getString(PROFILE_IMAGE, "")
    }

    fun getUserId(): String? {
        return sPrefManager.getString(ID, "")
    }


    companion object {
        const val PREF_NAME = "login_used_data_pref_manager"
        const val USER_NAME = "username"
        const val EMAIL = "email"
        const val ID = "id"
        const val PROFILE_IMAGE = "profile_image"
    }
}
