package com.prabhatkushwaha.mychef.framework.presentation.ui.onboarding

import com.prabhatkushwaha.mychef.business.domain.state.DataState
import com.prabhatkushwaha.mychef.business.domain.state.StateEvent
import com.prabhatkushwaha.mychef.business.interactors.onboarding.OnBoardingFragmentInteractors
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseViewModel
import com.prabhatkushwaha.mychef.framework.presentation.ui.onboarding.state.OnBoardingStateEvent
import com.prabhatkushwaha.mychef.framework.presentation.ui.onboarding.state.OnBoardingViewState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@InternalCoroutinesApi
class OnBoardingViewModel @Inject constructor(
    private val iterators: OnBoardingFragmentInteractors
) : BaseViewModel<OnBoardingViewState>() {
    override fun setStateEvent(stateEvent: StateEvent) {

    }

    override fun handleNewData(viewState: OnBoardingViewState) {

    }



    fun markToOldUser(): Boolean {
        return iterators.loginStatus.markUserAsOldUser()
    }

    override fun initNewViewState(): OnBoardingViewState {
       return OnBoardingViewState()
    }
}