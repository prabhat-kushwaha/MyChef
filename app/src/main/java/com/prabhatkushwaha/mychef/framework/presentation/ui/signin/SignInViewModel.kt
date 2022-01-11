package com.prabhatkushwaha.mychef.framework.presentation.ui.signin

import com.google.firebase.auth.FirebaseUser
import com.prabhatkushwaha.mychef.business.domain.state.DataState
import com.prabhatkushwaha.mychef.business.domain.state.StateEvent
import com.prabhatkushwaha.mychef.business.interactors.siginin.SignInFragmentInteractors
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseViewModel
import com.prabhatkushwaha.mychef.framework.presentation.ui.signin.state.SignInStateEvent
import com.prabhatkushwaha.mychef.framework.presentation.ui.signin.state.SignInViewState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@InternalCoroutinesApi
class SignInViewModel @Inject constructor(private val iterators: SignInFragmentInteractors) :
    BaseViewModel<SignInViewState>() {


    override fun handleNewData(viewState: SignInViewState) {

    }


    override fun initNewViewState(): SignInViewState {
        return SignInViewState()
    }

    override fun setStateEvent(stateEvent: StateEvent) {


    }

    fun saveSignInUserData(user: FirebaseUser):Boolean {
      return iterators.loginStatus.saveSignInUserData(user)
    }
}