package com.prabhatkushwaha.mychef.framework.presentation.ui.splash

import android.annotation.SuppressLint
import com.prabhatkushwaha.mychef.business.domain.state.DataState
import com.prabhatkushwaha.mychef.business.domain.state.StateEvent
import com.prabhatkushwaha.mychef.business.interactors.splash.SplashFragmentInteractors
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseViewModel
import com.prabhatkushwaha.mychef.framework.presentation.ui.recipedetails.state.DetailsFragmentViewState
import com.prabhatkushwaha.mychef.framework.presentation.ui.splash.state.SplashStateEvent
import com.prabhatkushwaha.mychef.framework.presentation.ui.splash.state.SplashViewState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@InternalCoroutinesApi
@SuppressLint("CustomSplashScreen")
class SplashScreenViewModel @Inject constructor(private val interactor: SplashFragmentInteractors) :
    BaseViewModel<SplashViewState>() {
    override fun setStateEvent(stateEvent: StateEvent) {
    }

    override fun handleNewData(viewState: SplashViewState) {
    }


    override fun initNewViewState(): SplashViewState {
        return SplashViewState()
    }

    fun isNewUser(): Boolean {
        return interactor.loginStatus.checkIsNewUser()
    }

    fun isSignIn(): Boolean {
        return interactor.loginStatus.checkIsLoggedIn()
    }


}


