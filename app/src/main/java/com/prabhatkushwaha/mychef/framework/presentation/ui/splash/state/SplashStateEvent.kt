package com.prabhatkushwaha.mychef.framework.presentation.ui.splash.state

import com.prabhatkushwaha.mychef.business.domain.state.StateEvent

sealed class SplashStateEvent : StateEvent {
    class CheckIsNewUserEvent() : StateEvent {
        override fun errorInfo(): String {
            return "User Details Not Found"
        }

        override fun stateEvent(): String {
            return "CheckIsNewUserEvent"
        }

        override fun shouldShowProgressDialog(): Boolean {
            return false
        }
    }
    class CheckIsLoggedInUserEvent() : StateEvent {
        override fun errorInfo(): String {
            return "User Not Found"
        }

        override fun stateEvent(): String {
            return "CheckIsLoggedInUserEvent"
        }

        override fun shouldShowProgressDialog(): Boolean {
            return false
        }
    }

}