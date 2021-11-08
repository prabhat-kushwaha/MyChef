package com.prabhatkushwaha.mychef.framework.presentation.ui.splash

import android.annotation.SuppressLint
import com.prabhatkushwaha.mychef.business.domain.state.StateEvent
import com.prabhatkushwaha.mychef.business.interactors.splash.SplashFragmentInteractors
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseViewModel
import com.prabhatkushwaha.mychef.framework.presentation.ui.splash.state.SplashViewState
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
class SplashScreenViewModel @Inject constructor(
    splashFragmentInteractors: SplashFragmentInteractors
) : BaseViewModel<SplashViewState>() {
    override fun setStateEvent(stateEvent: StateEvent) {

    }

    override fun handleNewData(viewState: SplashViewState) {

    }

    override fun initNewViewState(): SplashViewState {
        return SplashViewState()
    }

   /* fun isNewUser():Boolean{
        return loginStatusPref.getIsNewUser()
    }

    fun isSignIn():Boolean {
        return loginStatusPref.getIsSignIn()
    }*/
}