package com.prabhatkushwaha.mychef.framework.datasource.network.auth

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignIn

import android.R
import android.app.Activity
import android.util.Log
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.prabhatkushwaha.mychef.BuildConfig
import com.google.android.material.snackbar.Snackbar

import com.google.firebase.auth.FirebaseUser

import com.google.firebase.auth.AuthResult

import com.google.firebase.auth.GoogleAuthProvider

import com.google.firebase.auth.AuthCredential


@Singleton
class AuthHelper @Inject constructor(private val mAuth: FirebaseAuth) {
    private val TAG = "AuthHelper"

     fun signIn(activity: Activity, RC_SIGN_IN: Int) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.CLIENT_ID)
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(activity, gso)
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }


     fun firebaseAuthWithGoogle(
        idToken: String,
        getUserData: (firebaseUser: FirebaseUser?) -> Unit
    ) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithCredential:success${mAuth.currentUser?.displayName}")
                getUserData.invoke(mAuth.currentUser)
            } else {
                getUserData.invoke(null)
            }
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return mAuth.currentUser
    }

    fun signOut() {
        mAuth.signOut()
    }
}