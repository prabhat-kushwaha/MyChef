package com.prabhatkushwaha.mychef.framework.presentation.ui.signin.state

import android.os.Parcelable
import com.google.firebase.auth.FirebaseUser
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignInViewState(var user: FirebaseUser? = null, var isUserDataSaved: Boolean? = null) :
    Parcelable