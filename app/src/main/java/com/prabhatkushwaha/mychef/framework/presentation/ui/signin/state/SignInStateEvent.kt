package com.prabhatkushwaha.mychef.framework.presentation.ui.signin.state

import com.google.firebase.auth.FirebaseUser
import com.prabhatkushwaha.mychef.business.domain.state.StateEvent

sealed class SignInStateEvent : StateEvent {

    class SaveUserLoginData : StateEvent {
        override fun errorInfo(): String {
            return "An error occurred while save user data "
        }

        override fun stateEvent(): String {
            return "SaveUserLoginData"
        }

        override fun shouldShowProgressDialog(): Boolean {
            return false
        }
    }
}