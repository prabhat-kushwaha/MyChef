package com.prabhatkushwaha.mychef.business.interactors.common

import com.google.firebase.auth.FirebaseUser
import com.prabhatkushwaha.mychef.business.domain.state.DataState
import com.prabhatkushwaha.mychef.framework.pref.LoginStatusPrefManager
import com.prabhatkushwaha.mychef.framework.pref.UserDataPrefManager
import com.prabhatkushwaha.mychef.framework.presentation.ui.onboarding.state.OnBoardingStateEvent
import com.prabhatkushwaha.mychef.framework.presentation.ui.onboarding.state.OnBoardingViewState
import com.prabhatkushwaha.mychef.framework.presentation.ui.signin.state.SignInStateEvent
import com.prabhatkushwaha.mychef.framework.presentation.ui.signin.state.SignInViewState
import com.prabhatkushwaha.mychef.framework.presentation.ui.splash.state.SplashStateEvent
import com.prabhatkushwaha.mychef.framework.presentation.ui.splash.state.SplashViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginStatus @Inject constructor(
    private val loginStatusPrefManager: LoginStatusPrefManager,
    private val userDataPrefManager: UserDataPrefManager
) {


    fun checkIsNewUser(): Boolean {
        return loginStatusPrefManager.getIsNewUser()
    }

    fun checkIsLoggedIn(): Boolean {
        return loginStatusPrefManager.getIsSignIn()

    }

    fun markUserAsOldUser(): Boolean {
        return loginStatusPrefManager.setIsNewUser(false)
    }


    fun saveSignInUserData(user: FirebaseUser?): Boolean {
        return userDataPrefManager.saveUserData(user)
    }

}