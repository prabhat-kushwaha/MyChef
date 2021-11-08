package com.prabhatkushwaha.mychef.business.domain.state

interface StateEvent {

    fun errorInfo():String

    fun stateEvent():String

    fun shouldShowProgressDialog():Boolean
}