package com.prabhatkushwaha.mychef.framework.presentation.ui.onboarding.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OnBoardingViewState(var isUserStatusUpdated:Boolean?=null) : Parcelable