package com.prabhatkushwaha.mychef.framework.presentation.ui.onboarding.state

import com.prabhatkushwaha.mychef.business.domain.state.StateEvent

sealed class OnBoardingStateEvent : StateEvent {
    class MarkNewUserState() : StateEvent {
        override fun errorInfo(): String {
            return "An Error occurred while updating user status"
        }

        override fun stateEvent(): String {
            return "MarkNewUserState"
        }

        override fun shouldShowProgressDialog(): Boolean {
            return false
        }
    }
}