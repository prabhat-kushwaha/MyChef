package com.prabhatkushwaha.mychef.framework.presentation.ui.splash.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SplashViewState(
    var isNewUser:Boolean?=null,
    var isLoggedIn:Boolean?=null,
) : Parcelable