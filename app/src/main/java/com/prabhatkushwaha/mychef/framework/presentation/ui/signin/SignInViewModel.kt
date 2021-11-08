package com.prabhatkushwaha.mychef.framework.presentation.ui.signin

import com.prabhatkushwaha.mychef.business.domain.state.StateEvent
import com.prabhatkushwaha.mychef.business.interactors.siginin.SignInFragmentInteractors
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseViewModel
import com.prabhatkushwaha.mychef.framework.presentation.ui.signin.state.SignInViewState

class SignInViewModel(signInFragmentInteractors: SignInFragmentInteractors) : BaseViewModel<SignInViewState>() {


    override fun handleNewData(viewState: SignInViewState) {
        TODO("Not yet implemented")
    }

    override fun initNewViewState(): SignInViewState {
        TODO("Not yet implemented")
    }

    override fun setStateEvent(stateEvent: StateEvent) {
        TODO("Not yet implemented")
    }
}